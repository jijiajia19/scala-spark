package com.jacle.spark.core

object foldcase
{
  def main(args: Array[String]): Unit =
  {
    //测试foldleft的使用方法
    var list_a=List(1,2,3,4,5);

    //返回的类型要跟初始值类型一致，这是fold使用的注意点
    //常用处理集合->集合，RDD->RDD 但是类型要注意一致
    //使用fold可以来代替循环，从而使得我们在写数据处理的时候，实现函数式编程
    var result_left=list_a.foldLeft(0)((s,item)=>{
//      println("s->"+s)
      println("fold left item->"+item)
      s+item
    })

    //right的item、s跟left是相反的
    var result_right=list_a.foldRight(0)((item,s)=>{
//      println("s right->"+s)
      println("fold right item->"+item)
      s+item
    })

    var result_left_simple=(0/:list_a)(_+_)
    println("result_left_logogram->"+result_left_simple)

    var result_right_simple=(list_a:\100)((item,s)=>{s-item})
    println("result_right_logogram->"+result_right_simple)

    var m=1000
    var result=list_a.fold(m)((s,item)=>{
      println("s fold->"+s)
      val tmp=s+item
      tmp
    })

    println(result_left)
    println(result_right)
    println(result)
    //Range对象
    println((1 to 9))
    println((1 until 9))

  }

}
