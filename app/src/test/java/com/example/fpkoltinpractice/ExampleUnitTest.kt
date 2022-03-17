package com.example.fpkoltinpractice

import org.junit.Test

import org.junit.Assert.*

import com.example.fpkoltinpractice.FunList.Cons
import com.example.fpkoltinpractice.FunList.Nil

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun mainTest() {
        val list = Cons(1, Cons(2, Cons(3, Cons(4,
            Cons(5, Nil)
        ))))
    }
}