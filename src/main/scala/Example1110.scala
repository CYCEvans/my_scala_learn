object Example1110 extends App{
  class PersonOrdering extends Ordering[Person] {
    override def compare(x: Person, y: Person): Int = {
      if (x.name > y.name)
        1
      else
        -1
    }
  }

  case class Person(val name: String) {
    println("正在建構物件:" + name)
  }

  class Pair[T: Ordering](val first: T, val second: T) {
    //引入odering到Ordered的自動轉型
    //在查詢作用域範圍內的Ordering[T]的隱式值
    //本例的話是implicit val p1=new PersonOrdering
    //編譯器看到比較模式是<的模式進行的時候，會自動進行
    //自動轉型，轉換成Ordered，然後呼叫其中的<方法進行比較
    import Ordered.orderingToOrdered;

    def smaller = {
      if (first < second)
        first
      else
        second
    }
  }

  implicit val p1 = new PersonOrdering
  val p = new Pair(Person("123"), Person("456"))
  println(p.smaller)

}
