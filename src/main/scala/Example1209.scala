
/*
 * Typed Actor
 */
object Example1209 extends  App{

  import scala.concurrent.{ Promise, Future, Await }
  import akka.actor.{ TypedActor, TypedProps }
  import scala.concurrent.duration._
  import akka.event.Logging
  import akka.actor.ActorSystem

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

  class SquarerImpl(val name: String) extends Squarer {
    def this() = this("SquarerImpl")

    def squareDontCare(i: Int): Unit = i * i
    def square(i: Int): Future[Int] = Promise.successful(i * i).future
    def squareNowPlease(i: Int): Option[Int] = Some(i * i)
    def squareNow(i: Int): Int = i * i
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


  //fire-forget訊息傳送
  val ffSquare = mySquarer.squareDontCare(10)
  log.info("ffSquare="+ffSquare)
  //send-request-reply訊息傳送
  val oSquare = mySquarer.squareNowPlease(10)

  log.info("oSquare="+oSquare)

  val iSquare = mySquarer.squareNow(10)
  log.info("iSquare="+iSquare)

  //Request-reply-with-future 訊息傳送
  val fSquare = mySquarer.square(10)
  val result = Await.result(fSquare, 5 second)

  log.info("fSquare="+result)

  system.terminate()

}
