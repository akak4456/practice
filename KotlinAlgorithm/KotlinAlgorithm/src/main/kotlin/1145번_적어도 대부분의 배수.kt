import java.util.*

fun main(args: Array<String>) = with(Scanner(System.`in`)) {
    val arr = Array(5) { nextInt() }
    var result = 1
    while (true) {
        var cnt = 0
        for (input in arr) {
            if (result % input == 0) {
                cnt++
            }
        }
        if (cnt >= 3) {
            println(result)
            break
        }
        result++
    }
}