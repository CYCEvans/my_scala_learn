/**
 * Actor其它常用方法
 */
object Example1206 extends App{
  import akka.actor.{Actor, ActorSystem, Props}
  import akka.event.Logging;
  class StringActor extends Actor {
    val log = Logging(context.system, this);

    //建立Actor時呼叫，在接受和處理訊息前執行。主要用於Actor的起始化等工作
    override def preStart(): Unit = {
      log.info("preStart method in StringActor");
    }

    //Actor停止時呼叫的方法
    override def postStop(): Unit = {
      log.info("postStop method in StringActor");
    }

    //有未能處理的訊息時呼叫
    override def unhandled(message: Any): Unit = {
      log.info("unhandled method in StringActor")
      super.unhandled(message);
    }

    def receive = {
      case s:String ⇒ log.info("received message:\n"+s);
    }
  }

  val system = ActorSystem("StringSystem");

  //使用預設的建構函數建立Actor案例
  val stringActor = system.actorOf(Props[StringActor],name="StringActor");

  //給stringActor傳送字串訊息
  stringActor!"Creating Actors with default constructor";

  //給StringActor傳送整數資料，觸發呼叫unhandled方法
  stringActor!123;

  //關閉ActorSystem
  system.terminate();
}
