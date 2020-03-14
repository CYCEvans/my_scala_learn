object Example913 extends App{
  class Dog(val name:String,val age:Int);
  object Dog{
    def unapply(dog: Dog): Option[(String, Int)] = {
      if(dog!=null) {
        Some(dog.name, dog.age);
      }
      else {
        None;
      }
    }
  }
  val dog = new Dog("John", 2);

  def patternMatching(x:AnyRef)= x match {
    case Dog(name,age) => println(s"Dog's name is $name, age is $age");

    case _ =>
  }

  patternMatching(dog);

}
