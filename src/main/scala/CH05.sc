def gcd(x:Int, y:Int): Int=if(x%y==0) y else gcd(y,x%y)

val sum=(x:Int,y:Int)=>x+y
val sum2=(x:Int,y:Int)=>{println(x+y); x+y}
val f =(x:Double)=>x*2
lazy val f1 =(x:Double)=>x*2
var arrInt = Array(1,2,3,4)
val increment=(x:Int)=>x+1
arrInt.map(increment)
arrInt.map((x)=>x+1)
arrInt.map(_+1)
val increment1 = (_:Int)+1
val increment2:(Int)=> Int=1+_

def multiply(factor:Int)(x:Double) = factor*x;
multiply(50)(100);
val paf = multiply(50)_;
paf(10);
val paf2 = multiply _;
paf2(10)(10);
paf2(50);
def product(x1:Int)(x2:Int)(x3:Int)= x1*x2*x3;
def product_1=product(_:Int)(2)(3)
product_1(2);
val sample = 1 to 10;
val isEven: PartialFunction[Int,String]={
  case x if x%2==0 => x +" is even"
}

isEven(10);

//isEven(11);
sample.collect(isEven);

def receive:PartialFunction[Any,Unit]={
    case x:Int=>println("Int Type")
    case x:String=>println("Str Type")
    case _ => println("Other type")
}

receive(10)
receive("10")

val receive1:PartialFunction[Any,Unit]={
  case x:Int=>println("Int Type")
  case x:String=>println("Str Type")
  case _ => println("Other type")
}

receive1(10)
receive1("10")