object Example1212 extends App {
  import akka.dispatch.PriorityGenerator
  import akka.dispatch.UnboundedPriorityMailbox
  import akka.event.{Logging, LoggingAdapter}
  import com.typesafe.config.Config
  import com.typesafe.config.ConfigFactory
  import akka.actor.{Actor, ActorSystem, Props, PoisonPill};

  //自訂Mailbox,延伸自UnboundedPriorityMailbox
  class MyPrioMailbox(settings: ActorSystem.Settings, config: Config)
    extends UnboundedPriorityMailbox(
      // 建立PriorityGenerator，值越低表示優先級越高
      PriorityGenerator {
        // ’highpriority為符號訊息，首先處理（高優先級）
        case 'highpriority  =>0
        // ’lowpriority 為符號訊息，最後處理（低優先級）
        case 'lowpriority  =>2
        // PoisonPill 停止Actor執行
        case PoisonPill=>3
        // 預設優先級，值介於高優先級和低優先級之間
        case otherwise => 1
      })


  //從application.conf組態檔中載入dispatcher組態訊息
  val _system = ActorSystem.create("DsipatcherSystem",ConfigFactory.load().getConfig("MyDispatcherExample"))

  // We create a new Actor that just prints out what it processes
  val a = _system.actorOf(
    Props(new Actor {
      val log: LoggingAdapter = Logging(context.system, this)
      self ! 'lowpriority
      self ! 'lowpriority
      self ! 'highpriority
      self ! 'pigdog
      self ! 'pigdog2
      self ! 'pigdog3
      self ! 'highpriority
      self ! PoisonPill
      def receive = {
        case x => log.info(x.toString)
      }
    }).withDispatcher("defaultDispatcher"),name="UnboundedPriorityMailboxActor")

  _system.terminate()

}
