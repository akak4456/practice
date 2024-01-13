import java.util.*

fun main(args: Array<String>) = with(Scanner(System.`in`)) {
    val N = nextInt()
    val arr = Array(N) { nextInt() }
    val result: MutableSet<Int> = mutableSetOf()
    if (arr.size == 1) {
        println("A")
    } else {
        for (a in -300..300) {
            val b = arr[1] - arr[0] * a
            var isPossible = true
            for (idx in 2..<N) {
                if (arr[idx] != arr[idx - 1] * a + b) {
                    isPossible = false
                }
            }
            if (isPossible) {
                result.add(arr.last() * a + b)
            }
        }
        if (result.size > 1) {
            println("A")
        } else if (result.isEmpty()) {
            println("B")
        } else {
            println(result.first())
        }
    }
}