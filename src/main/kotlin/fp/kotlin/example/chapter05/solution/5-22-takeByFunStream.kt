package fp.kotlin.example.chapter05.solution

import fp.kotlin.example.chapter05.*

/**
 *
 * 연습문제 5-22
 *
 * 무한대를 표현한 FunStream에서 필요한 값을 가져오기 위해서 필요한 take 함수를 추가하자.
 * take 함수를 사용하여 무한 컬렉션에서 5개만 가져와서 합계를 구해 보자.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 *
 */

fun main() {
    require((1..100000000)
        .toFunStream()
        .take(1)
        .getHead() == 1)
    require(generateFunStream(0) { it + 5 }
        .take(5) == funStreamOf(0, 5, 10, 15, 20))
}

fun <T> FunStream<T>.take(n: Int): FunStream<T> = when {
    n < 0 -> throw IllegalArgumentException()
    n == 0 -> FunStream.Nil
    else -> when (this) {
        FunStream.Nil -> FunStream.Nil
        is FunStream.Cons -> FunStream.Cons(head) { tail().take(n - 1) }
    }
}