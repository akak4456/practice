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
        require(!EmptyTree.contains(5))

   val tree1 = EmptyTree.insert(5)
   require(tree1.contains(5))
    require(!tree1.contains(10))

    val tree2 = tree1.insert(3)
   require(tree2.contains(5))
    require(tree2.contains(3))
    require(!tree2.contains(10))

    val tree3 = tree2.insert(10)
    require(tree3.contains(5))
    require(tree3.contains(3))
    require(tree3.contains(10))
    }
}