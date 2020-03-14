object Example706 extends App{

  trait Logger{
    println("Logger");
  }
  trait FileLogger extends Logger{
    println("FileLogger");
  }
  trait Closable{
    println("Closable");
  }
  class Person{
    println("Construct Person!!");
  }
  class Student extends Person with FileLogger with Closable{
    println("Construct Stuent!!")
  }


    new Student();
}
