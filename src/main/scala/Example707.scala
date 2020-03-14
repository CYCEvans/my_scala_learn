object Example707 extends App{
  import java.io.PrintWriter

  trait Logger{
    def log(msg:String):Unit;
  }
  trait FileLogger extends Logger{

    val fileName:String;
    lazy val fileOutput = new PrintWriter(fileName:String);

    def log(msg:String):Unit={
      fileOutput.println(msg);
      fileOutput.flush();
    }
  }
  class Person;
  class Student extends Person with FileLogger{
    val fileName = "file.log";
  }

  // error
//  new Student().log("test demo");


// pre-defined
//  val s = new {
//  override val fileName = "file.log";
//} with Student
//  s.log("predefined")

  // lazy import
  val s = new Student;
  s.log("~~")
  s.log("lazy demo")

}
