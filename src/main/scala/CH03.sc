val x = 14
// if /else
if (x < 10) println(s"$x is smaller than 10") else println(s"$x is bigger than 10")
// for迴圈
for (i<- 1 to 5){
  println("x:" + i)
}
// range 1 到 5
1 to 5
// range 1 到 10，後面為步數
for (i<- 1 until(11,2)){
  println("x:" + i)
}
// to也有步數
for (i<- 1 to(11,2)){
  println("x:" + i)
}
// 條件迴圈
for (i<- 1 to 40 if (i%4==0);if(i%5==0)){
  println("x:" + i)
}
// yield 回傳值
var t1=for (i <- 1 to 5) yield i
var t2=for (i <- 1 to 5) yield i%2==0
var t3=for (i <- 1 to 5) yield i/2