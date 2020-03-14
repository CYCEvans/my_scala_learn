object Example1117 extends App{

  abstract class Food
  class Rice extends Food{
    override def toString="糧食"
  }
  class Meat extends Food{
    override def toString="肉"
  }

  class Animal{
    //定義了一抽象型態FoodType
    type FoodType
    //函數參數型態為FoodType
    def eat(f:FoodType)=f
  }
  class Human extends Animal{
    //子類別中確定其實際型態為Food
    type FoodType=Food
    //函數參數型態為FoodType，因為已經實際化了，所以為Food
    override  def eat(f:FoodType)=f

  }
  class Tiger extends Animal{
    //子類別中確定其實際型態為Meat
    type FoodType=Meat
    //函數參數型態為FoodType，因為已經實際化了，所以為Meat
    override  def eat(f:FoodType)=f
  }
  val human=new Human
  val tiger=new Tiger
  println("人可以吃："+human.eat(new Rice))
  println("人可以吃："+human.eat(new Meat))
  println("老虎只能吃："+tiger.eat(new Meat))
}
