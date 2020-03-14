object Example1103 extends App{
  val arrStr: Array[String] = Array("Hadoop", "Hive", "Spark")
  val arrInt: Array[Int] = Array(1, 2, 3)
  printAll(arrStr)
  printAll(arrInt)

  //透過_簡化設計，Array[_]與Array[T] forSome {type T}等值
  def printAll(x: Array[_]) = {
    for (i <- x) {
      print(i + " ")
    }
    println()
  }
}
