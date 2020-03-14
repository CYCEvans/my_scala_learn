object Example701 {
  trait Closeable{
    def close():Unit
  }
  class File(var name:String) extends java.io.File(name) with Closeable with Cloneable {
    def close(): Unit=println(s"File $name has been close")
  }

  def main(args: Array[String]): Unit = {
    new File("config.txt").close()
  }
}
