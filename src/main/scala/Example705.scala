object Example705 extends App{
  import java.io.PrintWriter

  trait Logger{
    println("Logger");
    def log(msg:String):Unit;
  }
  trait FileLogger extends Logger{
    println("FileLogger");
    val fileOutput = new PrintWriter("file.log");
    fileOutput.println("#");
    def log(msg:String):Unit={
      fileOutput.println(msg);
      fileOutput.flush();
    }
  }



//  def main(args: Array[String]): Unit = {
    new FileLogger {}.log("trait");
//  }
}
