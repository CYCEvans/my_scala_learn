/**
 * 停止Actor
 */

object Example1207 extends App{
  import akka.actor.{Actor, ActorSystem, Props}
  import akka.event.Logging;
  //定義自己的Actor，透過extends Actor並實現receive方法進行定義
  class StringActor extends Actor {
    val log = Logging(context.system, this)

    def receive = {
      case s:String => log.info("received message:\n"+s)
      case _      => log.info("received unknown message")
    }

    override def postStop(): Unit = {
      log.info("postStop in StringActor")
    }
  }

  //再定義一個Actor，在內定透過context建立Actor
  class ContextActor extends Actor{
    val log = Logging(context.system, this)
    //透過context建立StringActor
    var stringActor=context.actorOf(Props[StringActor],name="StringActor")
    def receive = {
      case s:String ⇒ {
        log.info("received message:\n"+s)

        stringActor!s
        //停止StringActor
        context.stop(stringActor)
      }
      case _ => log.info("received unknown message")
    }

    override def postStop(): Unit =  log.info("postStop in ContextActor")
  }


  //建立ActorSystem,ActorSystem為建立和查詢Actor的入口
  //ActorSystem管理的Actor共享組態訊息如分發器(dispatchers)、佈署（deployments）等
  val system = ActorSystem("StringSystem")


  //建立ContextActor
  val stringActor = system.actorOf(Props[ContextActor],name="ContextActor")

  //給stringActor傳送字串訊息
  stringActor!"Creating Actors with implicit val context"
  //關閉ActorSystem
//  system.terminate();
}
