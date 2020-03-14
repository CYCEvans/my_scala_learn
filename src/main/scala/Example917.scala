object Example917 extends App{
  //正規表示法
  val dateRegx ="""(\d\d\d\d)-(\d\d)-(\d\d)""".r

  //待比對的字串
  val text="2015-12-31 2016-02-20"

  //findFirstMatchIn傳回值型態為Option[Match]
  dateRegx.findFirstMatchIn(text) match{
    case Some(dateRegx(year,month,day))=>println(s"findFirstMatchIn與模式比對：year=$year,month=$month,day=$day")
    case None=>println("沒有找到比對")
  }

  //findFirstIn傳回值型態為Option[String]
  dateRegx.findFirstIn(text) match{
    case Some(dateRegx(year,month,day))=>println(s"findFirstIn與模式比對：year=$year,month=$month,day=$day")
    case None=>println("沒有找到比對")
  }
}
