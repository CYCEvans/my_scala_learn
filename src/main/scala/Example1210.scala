object Example1210 extends App{
  import akka.event.Logging
  import scala.concurrent.{ Promise, Future, Await }
  import akka.actor.{ TypedActor, TypedProps, ActorSystem }
  import akka.actor.TypedActor.{PostStop, PreStart}
  import scala.concurrent.duration._

  trait Squarer {
    //fire-and-forget訊息
    def squareDontCare(i: Int): Unit
    //非阻塞send-request-reply訊息
    def square(i: Int): Future[Int]
    //阻塞式的send-request-reply訊息
    def squareNowPlease(i: Int): Option[Int]
    //阻塞式的send-request-reply訊息
    def squareNow(i: Int): Int
  }


  //混入PostStop和PreStart
  class SquarerImpl(val name: String) extends  Squarer with PostStop with PreStart {
    import TypedActor.context
    val log = Logging(context.system,TypedActor.self.getClass())
    def this() = this("SquarerImpl")

    def squareDontCare(i: Int): Unit = i * i
    def square(i: Int): Future[Int] = Promise.successful(i * i).future
    def squareNowPlease(i: Int): Option[Int] = Some(i * i)
    def squareNow(i: Int): Int = i * i

    def postStop(): Unit={
      log.info ("TypedActor Stopped")
    }
    def preStart(): Unit={
      log.info ("TypedActor  Started")
    }
  }


  val system = ActorSystem("TypedActorSystem")
  val log = Logging(system, this.getClass)

  //使用預設建構函數建立Typed Actor
  val mySquarer: Squarer =
    TypedActor(system).typedActorOf(TypedProps[SquarerImpl](),"mySquarer")


  //使用非預設建構函數建立Typed Actor
  val otherSquarer: Squarer =
    TypedActor(system).typedActorOf(TypedProps(classOf[Squarer],
      new SquarerImpl("SquarerImpl")), "otherSquarer")

  //Request-reply-with-future 訊息傳送
  val fSquare = mySquarer.square(10)
  val result = Await.result(fSquare, 5 second)
  log.info("fSquare="+result)

  //呼叫poisonPill方法停止Actor執行
//  TypedActor(system).poisonPill(otherSquarer)

  //呼叫stop方法停止Actor執行
  TypedActor(system).stop(mySquarer)
}
