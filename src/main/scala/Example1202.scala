/**
  * 利用非預設建構函數建立 Actor
  */

object Example1202 extends App{
  import akka.actor.{Actor, ActorSystem, Props}
  import akka.event.Logging;

  // 自己定義 Actor ，通過extends 實現receive方法
  class StringActor(var name:String) extends Actor{
    val log = Logging(context.system, this);
    def receive= {
      case s:String => log.info("receive messages:\n" + s);
      case _ => log.info("unknown messages");
    }
  }

  //建立ActorSystem,ActorSystem為建立和查詢Actor的入口
  //ActorSystem管理的Actor共享組態訊息如分發器(dispatchers)、佈署（deployments）等
  val system = ActorSystem("StringSystem");
//  val sa = new StringActor("StringActor");
  //使用非預設的建構函數建立Actor案例，注意這裡是Props()，而非Props[]
  val stringActor = system.actorOf(Props(new StringActor("StringActor"))
    ,name="StringActor");

  //給stringActor傳送字串訊息
  stringActor!"Creating Actors with non-default constructor";

  //關閉ActorSystem
  system.terminate();
}
