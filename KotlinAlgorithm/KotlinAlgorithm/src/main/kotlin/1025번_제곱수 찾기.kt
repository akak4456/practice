import java.util.*
import kotlin.math.sqrt

fun getSquareNumber(A: Array<Array<Int>>, rowStride: Int, colStride: Int, rowStart: Int, colStart: Int): Int {
    var ans = -1
    var str = ""
    var rowIdx = rowStart
    var colIdx = colStart
    while (rowIdx >= 0 && colIdx >= 0 && rowIdx < A.size && colIdx < A[0].size) {
        str += A[rowIdx][colIdx]
        if (sqrt(str.toDouble()) == sqrt(str.toDouble()).toInt().toDouble()) {
            if (ans < str.toInt()) {
                ans = str.toInt()
            }
        }
        if (rowStride == 0 && colStride == 0) {
            break
        }
        rowIdx += rowStride
        colIdx += colStride
    }
    return ans
}

fun main(args: Array<String>) = with(Scanner(System.`in`)) {
    val N = nextInt()
    val M = nextInt()
    val A = Array<Array<Int>>(N) {
        Array<Int>(M) {
            0
        }
    }
    for (i in 0 until N) {
        val str = next()
        for (j in 0 until M) {
            A[i][j] = str[j] - '0'
        }
    }
    var ans = -1
    for (rowStride in -(N - 1)..<N) {
        for (colStride in -(M - 1)..<M) {
            for (rowStart in 0 until N) {
                for (colStart in 0 until M) {
                    val tmp = getSquareNumber(A, rowStride, colStride, rowStart, colStart)
                    if (ans < tmp) {
                        ans = tmp
                    }
                }
            }
        }
    }
    println(ans)
}