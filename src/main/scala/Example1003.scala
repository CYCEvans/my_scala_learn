object Example1003 extends App{
  //定義一個trait Multiplicable
  trait Multiplicable[T] {
    def multiply(x: T): T;
  }

  //定義一個普通類別MultiplicableInt，用於整數資料的相乘
  class MultiplicableInt extends Multiplicable[Int] {
    def multiply(x: Int) = x*x;
  }
  //定義一個普通類別MultiplicableString，用於字串資料的乘積
  class MultiplicableString extends Multiplicable[String] {
    def multiply(x: String) = x*2;
  }

  //使用隱式參數定義multiply函數
  def multiply[T: Multiplicable](x:T)(implicit  ev:Multiplicable[T]) = {
    //根據實際的型態呼叫對應的隱式物件中的方法
    ev.multiply(x);
  }

  //型態為MultiplicableInt的隱式值mInt
  implicit val mInt=new MultiplicableInt;

  //型態為MultiplicableString的隱式值mStr
  implicit val mStr=new MultiplicableString;

  //隱式值mInt當作參數傳入ev，給相當於呼叫multiply(5)(mInt)
  println(multiply(5));
  //隱式值mStr當作參數傳入ev，相當於呼叫multiply(5)(mStr)
  println(multiply("5"));
}
