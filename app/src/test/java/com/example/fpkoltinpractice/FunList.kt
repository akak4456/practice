package com.example.fpkoltinpractice

sealed class FunList<out T> {
    object Nil : FunList<Nothing>()
    data class Cons<out T>(val head: T, val tail: FunList<T>) : FunList<T>()
}

fun <T> FunList<T>.addHead(head: T): FunList<T> = FunList.Cons(head, this)

tailrec fun <T> FunList<T>.appendTail(value: T, acc: FunList<T> = FunList.Nil): FunList<T> =
    when (this) {
        FunList.Nil -> FunList.Cons(value, acc).reverse()
        is FunList.Cons -> tail.appendTail(value, acc.addHead(head))
    }

fun <T> FunList<T>.getHead(): T = when (this) {
    FunList.Nil -> throw NoSuchElementException()
    is FunList.Cons -> head
}

tailrec fun <T> FunList<T>.reverse(acc: FunList<T> = FunList.Nil): FunList<T> = when (this) {
    FunList.Nil -> acc
    is FunList.Cons -> tail.reverse(acc.addHead(head))
}

fun <T> FunList<T>.getTail(): FunList<T> = when (this) {
    FunList.Nil -> throw NoSuchElementException()
    is FunList.Cons -> tail
}

tailrec fun <T> FunList<T>.filter(acc: FunList<T> = FunList.Nil, p: (T) -> Boolean): FunList<T> =
    when (this) {
        FunList.Nil -> acc.reverse()
        is FunList.Cons -> if (p(head)) {
            tail.filter(acc.addHead(head), p)
        } else {
            tail.filter(acc, p)
        }
    }

fun <T> funListOf(vararg elements: T): FunList<T> = elements.toFunList()

private fun <T> Array<out T>.toFunList(): FunList<T> = when {
    this.isEmpty() -> FunList.Nil
    else -> FunList.Cons(this[0], this.copyOfRange(1, this.size).toFunList())
}

tailrec fun <T> FunList<T>.drop(n: Int): FunList<T> = when (this) {
    FunList.Nil -> this
    is FunList.Cons -> if (n == 0) this else tail.drop(n - 1)
}


tailrec fun <T> FunList<T>.dropWhile(p: (T) -> Boolean): FunList<T> = when {
    this == FunList.Nil -> this
    this is FunList.Cons && !p(this.head) -> tail.dropWhile(p)
    else -> this
}

tailrec fun <T> FunList<T>.take(n: Int, acc: FunList<T> = FunList.Nil): FunList<T> = when {
    n == 0 || this === FunList.Nil -> acc.reverse()
    else -> getTail().take(n - 1, acc.addHead(getHead()))
}

tailrec fun <T> FunList<T>.takeWhile(acc: FunList<T> = FunList.Nil, p: (T) -> Boolean): FunList<T> =
    when {
        !p(getHead()) || this === FunList.Nil -> acc.reverse()
        else -> getTail().takeWhile(acc.addHead(getHead()), p)
    }

tailrec fun <T, R> FunList<T>.map(acc: FunList<R> = FunList.Nil, f: (T) -> R): FunList<R> =
    when (this) {
        FunList.Nil -> acc.reverse()
        is FunList.Cons -> tail.map(acc.addHead(f(head)), f)
    }

tailrec fun <T, R> FunList<T>.indexedMap(
    index: Int = 0,
    acc: FunList<R> = FunList.Nil,
    f: (Int, T) -> R
): FunList<R> = when (this) {
    FunList.Nil -> acc.reverse()
    is FunList.Cons -> tail.indexedMap(index + 1, acc.addHead(f(index, head)), f)
}

tailrec fun <T, R> FunList<T>.foldLeft(acc: R, f: (R, T) -> R): R = when (this) {
    FunList.Nil -> acc
    is FunList.Cons -> tail.foldLeft(f(acc, head), f)
}

fun FunList<Int>.maximumByFoldLeft(): Int = foldLeft(0) { acc, value -> if(acc > value) acc else value }

fun <T> FunList<T>.filterByFoldLeft(p: (T) -> Boolean): FunList<T> = foldLeft(FunList.Nil){acc: FunList<T>,value -> if(p(value)) acc.appendTail(value) else acc}

fun <T, R> FunList<T>.foldRight(acc: R, f:(T,R) -> R): R = when(this){
    FunList.Nil -> acc
    is FunList.Cons -> f(head, tail.foldRight(acc,f))
}

fun <T> FunList<T>.reverseByFoldRight(): FunList<T> = this.foldRight(FunList.Nil) {x, acc:FunList<T> ->
    acc.appendTail(x)
}

fun <T> FunList<T>.filterByFoldRight(p: (T) -> Boolean): FunList<T> = this.foldRight(FunList.Nil) {x, acc: FunList<T> ->
    if(p(x)) acc.addHead(x) else acc
}

tailrec fun <T, R> FunList<T>.zip(other: FunList<R>, acc: FunList<Pair<T, R>> = FunList.Nil): FunList<Pair<T, R>> = when{
    this === FunList.Nil || other === FunList.Nil -> acc.reverse()
    else -> getTail().zip(other.getTail(),acc.addHead(Pair(this.getHead(), other.getHead())))
}

tailrec fun <T1, T2, R> FunList<T1>.zipWith(f: (T1, T2)->R, list:FunList<T2>, acc: FunList<R> = FunList.Nil) : FunList<R> = when{
    this === FunList.Nil || list === FunList.Nil -> acc.reverse()
    else -> getTail().zipWith(f,list.getTail(), acc.addHead(f(getHead(),list.getHead())))
}

fun <T, R> FunList<T>.associate(f: (T) -> Pair<T, R>): Map<T, R> = when{
    this === FunList.Nil -> mapOf()
    else -> mapOf(f(getHead())) + getTail().associate(f)
}

fun <T, K> FunList<T>.groupBy(f: (T) -> K): Map<K, FunList<T>> = foldRight(mapOf()){value, acc ->
    acc.plus(f(value) to (acc.getOrElse(f(value)){funListOf()}.addHead(value)))
}

tailrec fun <T> FunList<T>.toString(acc: String): String = when{
    this === FunList.Nil -> acc
    else -> {
        val addedString = if(acc==""){
            "[${getHead()}"
        }else if(getTail() === FunList.Nil){
            ", ${getHead()}]"
        }else {
            ", ${getHead()}"
        }
        getTail().toString(acc+addedString)
    }
}