/**
  * 訊息處理：?(Send-And-Receive-Future)
  */

object Example1205 extends App{
  import akka.actor.Actor;
  import akka.actor.Props;
  import akka.event.Logging;
  import akka.actor.ActorSystem;
  import scala.concurrent.Future;
  import akka.pattern.ask;
  import akka.util.Timeout;
  import scala.concurrent.duration._;
  import akka.pattern.pipe;
  import scala.concurrent.ExecutionContext.Implicits.global;

  //訊息：個人基礎訊息
  case class BasicInfo(id:Int,val name:String, age:Int);
  //訊息：個人興趣訊息
  case class InterestInfo(id:Int,val interest:String);
  //訊息: 完整個人訊息
  case class Person(basicInfo: BasicInfo,interestInfo: InterestInfo);


  //基礎訊息對應Actor
  class BasicInfoActor extends Actor{
    val log = Logging(context.system, this);
    def receive = {
      //處理送而來的使用者ID，然後將結果傳送給sender（本例中對應CombineActor）
      case id:Int => log.info("id="+id);sender!new BasicInfo(id,"John",19);
      case _      => log.info("received unknown message");
    }
  }

  //興趣愛好對應Actor
  class InterestInfoActor extends Actor{
    val log = Logging(context.system, this);
    def receive = {
      //處理送而來的使用者ID，然後將結果傳送給sender（本例中對應CombineActor）
      case id:Int => log.info("id="+id);sender!new InterestInfo(id,"足球");
      case _      => log.info("received unknown message");
    }
  }

  //Person完整訊息對應Actor
  class PersonActor extends Actor{
    val log = Logging(context.system, this);
    def receive = {
      case person: Person =>log.info("Person="+person);
      case _     => log.info("received unknown message");
    }
  }


  class CombineActor extends Actor{
    implicit val timeout = Timeout(5 seconds)
    val basicInfoActor = context.actorOf(Props[BasicInfoActor],name="BasicInfoActor");
    val interestInfoActor = context.actorOf(Props[InterestInfoActor],name="InterestInfoActor");
    val personActor = context.actorOf(Props[PersonActor],name="PersonActor");
    def receive = {
      case id: Int =>
        val combineResult: Future[Person] =
          for {
            //向basicInfoActor傳送Send-And-Receive-Future訊息，mapTo方法將傳回結果映射為BasicInfo型態
            basicInfo <- ask(basicInfoActor, id).mapTo[BasicInfo]
            //向interestInfoActor傳送Send-And-Receive-Future訊息，mapTo方法將傳回結果映射為InterestInfo型態
            interestInfo <- ask(interestInfoActor, id).mapTo[InterestInfo]
          } yield Person(basicInfo, interestInfo);

        //將Future結果傳送給PersonActor
        pipe(combineResult).to(personActor);

    }
  }

  val _system = ActorSystem("Send-And-Receive-Future");
  val combineActor = _system.actorOf(Props[CombineActor],name="CombineActor");
  combineActor ! 12345;
  Thread.sleep(5000);
  //關閉ActorSystem
  _system.terminate();
}
