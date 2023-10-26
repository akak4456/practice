package com.example.fpkoltinpractice

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun mainTest() {
        val funList: FunList<Int> = Cons(1, Cons(2, Cons(3, Nil)))

        require(funList.fmap { it * 3 } ==
                Cons(3, Cons(6, Cons(9, Nil))))
        require(funList.first() == 1)
        require(funList.size() == 3)

        val funList2: FunList<Int> = Nil

        require(funList2.fmap { it * 3 } == Nil)
//    require(funList2.first()  throw NoSuchElementException())
        require(funList2.size() == 0)
    }
}