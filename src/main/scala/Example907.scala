object Example907 extends App{
  case class Dog(name:String, age:Int);
  val dog = new Dog("John", 2);
  val arrInt = Array(1,2,3,4);
  val tupleInt = (1,2,3,4);
  def patternMatching(x:AnyRef)= x match {
    case Dog(name,age) => println(s"Dog's name is $name, age is $age");

    case _ =>
  }
  def patternMatching2(x:AnyRef)= x match {
    case Dog(_,age) => println(s"Dog age is $age");

    case _ =>
  }
  def patternMatching3(x:AnyRef)= x match {
    case Array(first,second) => println(s"First array element is $first," +
      s" second array element is $second");
    case Array(first,_,three,_*) => println(s"First array element is $first," +
      s" third array element is $three");
    case _ =>
  }
  def patternMatching4(x:AnyRef)= x match {
    case (first,second) => println(s"First tuple element is $first," +
      s" second tuple element is $second");
    case (first,_,three,_) => println(s"First tuple element is $first," +
      s" third tuple element is $three");
    case _ =>
  }
  patternMatching(dog);
  patternMatching2(dog);
  patternMatching3(arrInt);
  patternMatching4(tupleInt);

}
