package com.example.fpkoltinpractice

sealed class Tree<out T>

data class Node<T>(
    val value: T,
    val leftTree: Tree<T> = EmptyTree,
    val rightTree: Tree<T> = EmptyTree
) : Tree<T>()

object EmptyTree : Tree<Nothing>()

fun Tree<Int>.insert(elem: Int): Tree<Int> =
    when (this) {
        EmptyTree -> Node(elem, EmptyTree, EmptyTree)
        is Node -> when {
            elem <= value -> Node(value, leftTree.insert(elem), rightTree)
            else -> Node(value, leftTree, rightTree.insert(elem))
        }
    }


fun Tree<Int>.contains(elem: Int): Boolean = when(this) {
    EmptyTree -> false
    is Node -> when {
        elem == value -> true
        elem < value -> leftTree.contains(elem)
        else -> rightTree.contains(elem)
    }
}