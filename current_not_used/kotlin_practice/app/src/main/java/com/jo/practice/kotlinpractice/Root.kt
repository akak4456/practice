package com.jo.practice.kotlinpractice

fun printContents(list:List<Any>){
    println(list.joinToString())
}

fun addAnswer(list:MutableList<Any>){
   list.add(42)
}