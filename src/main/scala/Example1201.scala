/**
  * 利用預設建構函數建立 Actor
  */

object Example1201 extends App{
  import akka.actor.Actor;
  import akka.actor.Props;
  import akka.event.Logging;
  import akka.actor.ActorSystem;

  // 自己定義 Actor ，通過extends 實現receive方法
  class StringActor extends Actor{
    val log = Logging(context.system, this);
    def receive= {
      case s:String => log.info("receive messages:" + s);
      case _ => log.info("unknown messages");
    }
  }

  //建立ActorSystem,ActorSystem為建立和查詢Actor的入口
  //ActorSystem管理的Actor共享組態訊息如分發器(dispatchers)、佈署（deployments）等
  val system = ActorSystem("StringSystem");

  //使用預設的建構函數建立Actor案例
  val stringActor = system.actorOf(Props[StringActor]);
//  val stringActor = system.actorOf(Props[StringActor], name = "StringActor");


  //給stringActor傳送字串訊息
  stringActor!"Creating Actors with default constructor";

  //關閉ActorSystem
  system.terminate();
}
