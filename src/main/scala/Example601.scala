object Example601{
  object Student{
    private var studentNo:Int=_;
    def uniqueStudentNo()={
      studentNo+=1
      studentNo
    }
  }
  class Person(val name:String,val age:Int){
    println("Constructing Person....");

    override def toString()= name + ":" + age;
  }

  def main(args: Array[String]) = {
//    println(Student.uniqueStudentNo())
    val p = new Person("john",29);
    println(p);
  }
}
