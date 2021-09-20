package com.jo.practice.kotlinpractice

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun componentTest(){
        val (name,ext) = splitFilename("example.kt")
        println(name)
        println(ext)
    }
}