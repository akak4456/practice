import java.lang.Exception
import java.util.*

fun main(args: Array<String>) = with(Scanner(System.`in`)) {
    val N = nextInt()
    val K = nextInt()

    var kString = ""
    var kStringOneCount = 0
    for (ch in N.toString(2)) {
        if (ch == '1') {
            kString += if (kStringOneCount < K) {
                "1"
            } else {
                "0"
            }
            kStringOneCount++
        } else {
            kString += "0"
        }
    }
    if (K >= N) {
        println(0)
    } else {
        while (kString.toInt(2) < N) {
            var tmpK = kString.toInt(2)
            tmpK += kString.substring(kString.lastIndexOf('1')).toInt(2)
            kString = tmpK.toString(2)
        }
        println((kString.toInt(2) - N))
    }
}