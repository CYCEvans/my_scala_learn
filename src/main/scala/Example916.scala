object Example916 extends App{
  //正規表示法
  val dateRegx ="""(\d\d\d\d)-(\d\d)-(\d\d)""".r

  //待比對的字串
  val text="2015-12-31 2016-02-20"

  //分析模式的分群組訊息
  for (date<- dateRegx.findAllIn(text)) {
    date match {
      case dateRegx(year,month,day)=>println(s"match敘述中的模式比對：year=$year,month=$month,day=$day")
      case _=>
    }
  }

  //分析模式的分群組訊息，與前面的程式碼作用等同
  for (dateRegx(year,month,day)<- dateRegx.findAllIn(text)) {
    println(s"for循環中的正規表示法模式比對：year=$year,month=$month,day=$day")
  }
}
