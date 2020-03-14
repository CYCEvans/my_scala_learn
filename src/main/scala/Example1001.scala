object Example1001 extends App{

  //定義一個trait Multiplicable
  trait Multiplicable[T] {
    def multiply(x: T): T;
  }

  //定義一個隱式物件MultiplicableInt，用於整數資料的相乘
  implicit object MultiplicableInt extends Multiplicable[Int] {
    def multiply(x: Int) = x*x;
  }
  //定義一個隱式物件MultiplicableString，用於字串資料的乘積
  implicit object MultiplicableString extends Multiplicable[String] {
    def multiply(x: String) = x*2;
  }

  //定義一個函數，函數具有泛型參數
//  def multiply[T: Multiplicable](x:T) = {
//    //implicitly方法，存取隱式物件
//    val ev = implicitly[Multiplicable[T]];
//    //根據實際的型態呼叫對應的隱式物件中的方法
//    ev.multiply(x);
//  }
  //使用隱式參數定義multiply函數
  def multiply[T: Multiplicable](x:T)(implicit  ev:Multiplicable[T]) = {
    //根據實際的型態呼叫對應的隱式物件中的方法
    ev.multiply(x)
  }

  //呼叫隱式物件MultiplicableInt中的multiply方法
  println(multiply(5));
  //呼叫隱式物件MultiplicableString中的multiply方法
  println(multiply("5"));
}
