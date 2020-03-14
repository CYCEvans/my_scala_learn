object Example909 extends App{
  class A;
  class B extends A;
  class C extends A;
  val b = new B;
  val c = new C;

  def patternMatching(x:AnyRef)= x match {
    case x:String => println(s"String");
    case x:B => println(s"B");
    case x:A => println(s"A");
    case _ =>
  }
  patternMatching("sss");
  patternMatching(b);
  patternMatching(c);
}
