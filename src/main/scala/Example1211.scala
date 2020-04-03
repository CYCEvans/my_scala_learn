object Example1211 extends App{
  import akka.actor.Actor;
  import akka.event.Logging
  import akka.actor.ActorSystem
  import com.typesafe.config.ConfigFactory
  import akka.actor.Props

  class StringActor extends Actor {
    val log = Logging(context.system, this)

    def receive = {
      case s:String => {
        log.info("received message:\n"+s)
      };
      case _ => log.info("received unknown message")
    }

    override def postStop(): Unit = {
      log.info("postStop in StringActor")
    }
  }

  //從application.conf組態檔中載入dispatcher組態訊息
  val _system = ActorSystem.create("DsipatcherSystem",ConfigFactory.load().getConfig("Akka-Default-Dsipatcher-Example"))

  //建立Actor時透過withDispatcher方法指定自訂的Dispatcher
  val stringActor = _system.actorOf(Props[StringActor].withDispatcher("defaultDispatcher"),name="StringActor")
  val stringActor1 = _system.actorOf(Props[StringActor].withDispatcher("defaultDispatcher"),name="StringActor1")
  stringActor!"Test"
  stringActor1!"StringActor1"

  _system.terminate()
}
