package com.jo.practice.kotlinpractice

fun twoAndThree(operation: (Int, Int) -> Int){
    val result = operation(2,3)
    println("The result is $result")
}