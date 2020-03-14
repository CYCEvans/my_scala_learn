/**
  * 訊息處理：!(Fire-Forget)
  */

object Example1204 extends App{
  import akka.actor.{Actor, ActorSystem, Props}
  import akka.event.Logging;
  case class Start(var msg:String);
  case class Run(var msg:String);
  case class Stop(var msg:String);

  class ExampleActor extends Actor {
    val other = context.actorOf(Props[OtherActor], "OtherActor");
    val log = Logging(context.system, this);
    def receive={
      //使用fire-and-forget訊息模型向OtherActor傳送訊息，隱式地傳遞sender
      case Start(msg) => other ! msg;
      //使用fire-and-forget訊息模型向OtherActor傳送訊息，直接呼叫tell方法，顯性指定sender
      case Run(msg) => other.tell(msg, sender);
    }
  }

  class OtherActor extends  Actor{
    val log = Logging(context.system, this);
    def receive ={
      case s:String => log.info("received message:\n"+s);
      case _ => log.info("received unknown message");
    }
  }

  //建立ActorSystem,ActorSystem為建立和查詢Actor的入口
  //ActorSystem管理的Actor共享組態訊息如分發器(dispatchers)、佈署（deployments）等
  val system = ActorSystem("MessageProcessingSystem");

  //建立ContextActor
  val exampleActor = system.actorOf(Props[ExampleActor],name="ExampleActor");

  //使用fire-and-forget訊息模型向exampleActor傳送訊息
  exampleActor!Run("Running");
  exampleActor!Start("Starting");


  //關閉ActorSystem
  system.terminate();
}
