package com.example.fpkoltinpractice

import org.junit.Test

import org.junit.Assert.*

import com.example.fpkoltinpractice.FunList.Cons
import com.example.fpkoltinpractice.FunList.Nil
import com.example.fpkoltinpractice.FunStream.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun mainTest() {
        print(funListOf(1, 2, 3, 4, 5).toString(""))
        require(funListOf(1, 2, 3, 4, 5).toString("") == "[1, 2, 3, 4, 5]")
    }
}