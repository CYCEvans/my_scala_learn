object Example1106 extends App{
  //宣告Person類別為case class且實現了Comparable接口
  case class Person(var name: String, var age: Int) extends Comparable[Person] {
    def compareTo(o: Person): Int = {
      if (this.age > o.age) 1
      else if (this.age == o.age) 0
      else -1
    }
  }

  class TypeVariableBound {
    def compare[T <: Comparable[T]](first: T, second: T) = {
      if (first.compareTo(second) > 0)
        first
      else
        second
    }
  }

  val tvb = new TypeVariableBound
  println(tvb.compare("A", "B"))
  //此時下面這條敘述是合法的，因為Person類別實現了Comparable接口
  println(tvb.compare(Person("stephen", 19), Person("john", 20)))


  //定義Student類別為case class，且泛型T的型態變數界定為AnyVal
  //在建立類別時，所有處於AnyVal類別繼承階層結構的類別都是合法的，如Int、Double相等型態
  case class Student[S, T <: AnyVal](var name: S, var hight: T)

  //下面這條敘述是非法的，因為String型態不屬於AnyVal類別階層結構
  //  val S1=Student("john","170")
  //下面這兩條敘述都是合法的，因為Int,Long型態都是AnyVal
  val S2 = Student("john", 170.0)
  val S3 = Student("john", 170L)
}
