object Example912 extends App{

  val list = List(List(1,2,3,4,5),List(5,6,7,8,9));

  def patternMatching(x:AnyRef)= x match {
    case e1@List(_,e2@List(5,_*))=> println("e1="+e1+"\ne2="+e2);

    case _ =>
  }
  patternMatching(list);

}
