object Example1116 extends App{

  class Outter{
    val x:Int=0
    def test(i:Outter#Inner)=i
    class Inner
  }

  val o1=new Outter
  val o2=new Outter

  val inner2=new o2.Inner
  val inner1=new o1.Inner

  //編譯透過
  o1.test(inner1)
  //編譯透過
  o1.test(inner2)

}
