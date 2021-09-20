package com.jo.practice.kotlinpractice

fun splitFilename(fullName:String):NameComponents{
    val (name,ext) = fullName.split('.',limit = 2)
    return NameComponents(name,ext)
}