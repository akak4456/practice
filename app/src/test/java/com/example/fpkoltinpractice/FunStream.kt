package com.example.fpkoltinpractice

sealed class FunStream<out T> {
    object Nil : FunStream<Nothing>()
    data class Cons<out T>(val head: () -> T, val tail: () -> FunStream<T>) : FunStream<T>() {
        override fun equals(other: Any?): Boolean =
            if (other is Cons<*>) {
                if (head() == other.head()) {
                    tail() == other.tail()
                } else {
                    false
                }
            } else {
                false
            }

        override fun hashCode(): Int {
            var result = head.hashCode()
            result = 31 * result + tail.hashCode()
            return result
        }
    }
}

fun <T> FunStream<T>.getHead(): T = when(this){
    FunStream.Nil -> throw NoSuchElementException()
    is FunStream.Cons -> head()
}

fun <T> FunStream<T>.getTail(): FunStream<T> = when (this){
    FunStream.Nil -> throw NoSuchElementException()
    is FunStream.Cons -> tail()
}

fun <T> funStreamOf(vararg elements: T): FunStream<T> = elements.toFunStream()

fun <T> Array<out T>.toFunStream(): FunStream<T> = when {
    this.isEmpty() -> FunStream.Nil
    else -> FunStream.Cons({ this[0] }, { this.copyOfRange(1, this.size).toFunStream() })
}

fun FunStream<Int>.sum(): Int = when{
    this === FunStream.Nil -> 0
    else -> getHead() + getTail().sum()
}

tailrec fun <T, R> FunStream<T>.foldLeft(acc: R, f: (R, T) -> R): R = when (this) {
    FunStream.Nil -> acc
    is FunStream.Cons -> tail().foldLeft(f(acc, head()), f)
}

fun FunStream<Int>.product(): Int = foldLeft(1){acc, value -> acc * value}

fun <T> FunStream<T>.appendTail(value: T): FunStream<T> = when (this) {
    FunStream.Nil -> FunStream.Cons({ value }, { FunStream.Nil })
    is FunStream.Cons -> FunStream.Cons(head, { tail().appendTail(value) })
}

fun <T> FunStream<T>.filter(p: (T) -> Boolean): FunStream<T> = when (this){
    FunStream.Nil -> FunStream.Nil
    is FunStream.Cons -> if(p(head())) FunStream.Cons(head, {tail().filter(p)}) else tail().filter(p)
}

fun <T, R> FunStream<T>.map(f: (T) -> R): FunStream<R> = when(this){
    FunStream.Nil -> FunStream.Nil
    is FunStream.Cons -> FunStream.Cons({f(head())},{tail().map(f)})
}