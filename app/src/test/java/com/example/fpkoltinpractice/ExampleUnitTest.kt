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
        val list = funListOf(1, 2, 3, 4, 5)
        require(list.filterByFoldRight { it % 2 == 0 } == funListOf(2, 4))
        require(list.filterByFoldRight { it < 1 } == FunList.Nil)
        require(list.filterByFoldRight { it < 6 } == funListOf(1, 2, 3, 4, 5))
    }
}