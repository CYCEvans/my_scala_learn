object Example1109 extends App{
  //使用的是型態變數界定S <: Comparable[S]
//  case class Student[T, S <: Comparable[S]](var name: T, var height: S)

  //利用<%符號對泛型S進行限定，它的意思是S可以是Comparable類別繼承階層結構
  //中實現了Comparable接口的類別也可以是能夠經由自動轉型得到的類別,該類別實現了Comparable接口
  case class Student[T, S <% Comparable[S]](var name: T, var height: S)

  val s = Student("john", "170")
  //下面這條敘述在檢視界定中是合法的因為Int型態此時會自動轉型為RichInt類別，
  //而RichInt類別實現了Comparable接口，屬於Comparable繼承階層結構
  val s2 = Student("john", 170)
}
