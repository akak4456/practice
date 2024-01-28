import java.lang.Exception
import java.util.*
import kotlin.math.pow

fun main(args: Array<String>) = with(Scanner(System.`in`)) {
    val s = nextInt()
    val N = nextInt()
    val K = nextInt()
    val R1 = nextInt()
    val R2 = nextInt()
    val C1 = nextInt()
    val C2 = nextInt()
    for (row in R1..R2) {
        for (col in C1..C2) {
            print(getCellInfo(N, K, row, col, s))
        }
        println()
    }
}

/**
 * getCellInfo(3, 1, 0, 0, 1) 은 0이다(하얀색)
 * getCellInfo(3, 1, 1, 1, 1) 은 1이다(검은색)
 */
fun getCellInfo(N: Int, K: Int, row: Int, col: Int, s: Int): Int {
    if (s == 0) {
        // 이 경우는 볼것도 없이 당연히 하얀색이다.
        return 0
    }
    val priorCellInfo = getCellInfo(N, K, row / N, col / N, s - 1)
    return if (priorCellInfo == 1) {
        1
    } else {
        if (row % N in (N / 2 - K / 2)..(N / 2 + K / 2 - if (N % 2 == 0) 1 else 0) &&
            col % N in (N / 2 - K / 2)..(N / 2 + K / 2 - if (N % 2 == 0) 1 else 0)
        ) {
            1
        } else {
            0
        }
    }
}