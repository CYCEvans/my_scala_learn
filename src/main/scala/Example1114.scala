object Example1114 extends App{
  class Pet{
    private var name:String=null
    private var weight:Float=0.0f

    def setName(name:String)={
      this.name=name
      //傳回呼叫物件，型態為Pet
      this
    }
    def setWeight(weight:Float)={
      this.weight=weight
      //傳回呼叫物件，型態為Pet
      this
    }
    override  def toString=s"name=$name,age=$weight"
  }

  class Dog extends Pet{
    private var age:Int=0
    def setAge(age:Int)={
      this.age=age
      //傳回呼叫物件，型態為Dog
      this
    }
    override  def toString=super.toString+s",age=$age"
  }

  //程式碼能夠順利執行
  println(new Dog().setAge(2).setName("Nacy").setWeight(20.0f))

  //編譯顯示出錯，Error:(33, 54) value setAge is not a member of cn.scala.chapter10.Example11_11.Pet
  //println(new Dog().setName("Nacy").setWeight(20.0f).setAge(2))
}
