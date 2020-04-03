

import scala.concurrent.Await

object Example1214 extends App {
  import akka.actor.actorRef2Scala
  import akka.actor.Actor
  import akka.actor.ActorLogging
  import akka.actor.Props
  import scala.concurrent.duration._
  import akka.actor.ActorSystem
  import akka.util.Timeout
  import akka.pattern.ask
  import akka.actor.OneForOneStrategy
  import akka.actor.SupervisorStrategy._

  case class NormalMessage()
  class ChildActor extends Actor with ActorLogging {
    var state: Int = 0

    override def preStart() {
      log.info("啟動 ChildActor，其hashcode為"+this.hashCode())
    }
    override def postStop() {
      log.info("停止 ChildActor，其hashcode為"+this.hashCode())
    }
    def receive: Receive = {
      case value: Int =>
        if (value <= 0)
          throw new ArithmeticException("數字小於等於0")
        else
          state = value
      case result: NormalMessage =>
        sender ! state
      case ex: NullPointerException =>
        throw new NullPointerException("空指標")
      case _ =>
        throw new IllegalArgumentException("非法參數")
    }
  }

  class SupervisorActor extends Actor with ActorLogging {

    val childActor = context.actorOf(Props[ChildActor], name = "ChildActor")
    override val supervisorStrategy = OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 10 seconds) {
      //例外型態為ArithmeticException時，使用Resume機制
      case _: ArithmeticException => Resume
      //例外型態為NullPointerException時，使用Restart機制
      case _: NullPointerException => Restart
      //例外型態為IllegalArgumentException時，使用Stop機制
      case _: IllegalArgumentException => Stop
      //其它例外機制，使用Escalate機制
      case _: Exception => Escalate
    }

    def receive = {
      case msg: NormalMessage =>
        childActor.tell(msg, sender)
      case msg: Object =>
        childActor ! msg

    }
  }

  val system = ActorSystem("FaultToleranceSystem")
  val log = system.log

  val supervisor = system.actorOf(Props[SupervisorActor], name = "SupervisorActor")

  //正數，訊息標準處理
  var mesg: Int = 5
  supervisor ! mesg

  implicit val timeout = Timeout(5 seconds)
  var future = (supervisor ? new NormalMessage).mapTo[Int]
  var resultMsg = Await.result(future, timeout.duration)

  log.info("結果:"+resultMsg)

  //負數，Actor會拋出例外，Superrvisor使用Resume處理機制
  mesg = -5
  supervisor ! mesg


  future = (supervisor ? new NormalMessage).mapTo[Int]
  resultMsg = Await.result(future, timeout.duration)

  log.info("結果:"+resultMsg)


  //空指標訊息，Actor會拋出例外，Superrvisor使用restart處理機制
  supervisor ! new NullPointerException

  future = (supervisor ? new NormalMessage).mapTo[Int]
  resultMsg = Await.result(future, timeout.duration)

  log.info("結果:"+resultMsg)

  //String型態參數為非法參數，Actor會拋出例外，Superrvisor使用stop處理機制
  supervisor ? "字串"

  system.terminate()
}
