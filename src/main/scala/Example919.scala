object Exampel919 extends  App{
  //定義一個sealed trait DeployMessage
  sealed trait DeployMessage;

  //定義三個實際的子類別，全部為case class
  case class RegisterWorker(id: String, host: String, port: Int) extends DeployMessage;
  case class UnRegisterWorker(id: String, host: String, port: Int) extends DeployMessage;
  case class Heartbeat(workerId: String) extends DeployMessage;

  //handleMessage函數會處理所有可能的情況，即窮列出所有DeployMessage的子類別
  def handleMessage(msg:DeployMessage)= msg match {
    case RegisterWorker(id,host,port)=>s"The worker $id is registering on $host:$port";
    case UnRegisterWorker(id,host,port)=>s"The worker $id is unregistering on $host:$port";
//    case Heartbeat(id)=>s"The worker $id is sending heartbeat"
  }

  val msgRegister=RegisterWorker("204799","192.168.1.109",8079);
  val msgUnregister=UnRegisterWorker("204799","192.168.1.109",8079);
//  val msgHeartbeat=Heartbeat("204799")

  println(handleMessage(msgRegister));
  println(handleMessage(msgUnregister));
}
