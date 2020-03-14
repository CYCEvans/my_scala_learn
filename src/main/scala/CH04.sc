var strArray = new Array[String](10)
strArray(0) = "First Element"
strArray
val strArray2 = Array("First","Second")

import scala.collection.mutable.ArrayBuffer
val strArrarVar = new ArrayBuffer[String]()
strArrarVar += "Hello"
strArrarVar += ("World", "Programmer")
strArrarVar ++= Array("Wellcom", "To", "Scala World")
strArrarVar ++= List("Wellcom", "To", "Scala World")
strArrarVar.trimEnd(3)
strArrarVar

var intArrarVar = ArrayBuffer(1,1,2)
intArrarVar.insert(0, 6)
intArrarVar
intArrarVar.insert(0, 7, 8, 9)
intArrarVar
intArrarVar.remove(0,4)
intArrarVar

intArrarVar.toArray
strArrarVar.toBuffer

for (i <- 0 to intArrarVar.length -1 ) println ("Array Element: " +
intArrarVar(i))
for (i <- 0 until  intArrarVar.length  ) println ("!!Array Element: " +
  intArrarVar(i))
for (i <- 0 until  (3, 2)) println ("Array Element: " +
  intArrarVar(i))
for (i <- (0 until  intArrarVar.length).reverse) println ("Array Element: " +
  intArrarVar(i))
for (i <- intArrarVar) println ("Array Element: " + i)

var intArrayVar2 = for(i <- intArrarVar) yield i *2
var intArrayNoBuffer = Array(1,2,3)
var intArrayNoBuffer2 = for(i <- intArrayNoBuffer if i>=2) yield i *2

val intArr = Array(1,2,3,4,5,6,7,8,9,10)
intArr.sum
intArr.max
ArrayBuffer("Hello","Hell","Hey","Happy").max
intArr.min
intArr.toString()
intArr.mkString(",")
intArr.mkString("<",",",">")

var multiDimArr = Array(Array(1,2,3), Array(2,3,4))

multiDimArr(0)(2)

for (i <- multiDimArr) println(i.mkString(","))

for (i <- multiDimArr)
  for (j <- i) print(j+ " ")

val nums = 1::2::3::4::Nil

nums.isEmpty

nums.head

nums.tail

nums.tail.head

nums.init

//extend
List(1,2,3):::List(4,5,6)


