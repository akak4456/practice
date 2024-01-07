import java.util.*

var r1 = 0
var c1 = 0
var r2 = 0
var c2 = 0
val partOfBoard: Array<IntArray> = Array(50) {
    IntArray(5) {
        -1
    }
}

fun main(args: Array<String>) = with(Scanner(System.`in`)) {
    r1 = nextInt()
    c1 = nextInt()
    r2 = nextInt()
    c2 = nextInt()
    makeStorm(1, 0, 0, 0, 1, 1, false)

    var maxLength = -1

    for (row in 0..<(r2 - r1 + 1)) {
        for (col in 0..<(c2 - c1 + 1)) {
            if (partOfBoard[row][col].toString().length > maxLength) {
                maxLength = partOfBoard[row][col].toString().length
            }
        }
    }
    for (row in 0..<(r2 - r1 + 1)) {
        for (col in 0..<(c2 - c1 + 1)) {
            val s = partOfBoard[row][col].toString()
            print(s.padStart(maxLength))
            print(" ")
        }
        println()
    }
}

val dxAndDy: Array<Pair<Int, Int>> = arrayOf(
    Pair(1, 0),
    Pair(0, -1),
    Pair(-1, 0),
    Pair(0, 1)
)

/**
 * nextDirection
 * 0: 왼쪽으로 간다
 * 1: 위쪽으로 간다
 * 2: 오른쪽으로 간다
 * 3: 아래쪽으로 간다
 */
tailrec fun makeStorm(
    cur: Int,
    row: Int,
    col: Int,
    nextDirection: Int,
    remainStep: Int,
    maxRemainStep: Int,
    isTurn: Boolean
) {
    if (row in (r1..r2) && col in (c1..c2)) {
        partOfBoard[row - r1][col - c1] = cur
    }
    var targetNewRow = -9999
    var targetNewCol = -9999
    var targetNextDirection = -9999
    var targetRemainStep = -9999
    var targetMaxRemainStep = -9999
    var targetIsTurn = isTurn
    for (newNextDirection in 0..<4) {
        if (nextDirection == newNextDirection) {
            val newRow = row + dxAndDy[newNextDirection].second
            val newCol = col + dxAndDy[newNextDirection].first
            if (isValidRowAndCol(newRow, newCol)) {
                if (remainStep > 0) {
                    targetNewRow = newRow
                    targetNewCol = newCol
                    targetNextDirection = nextDirection
                    targetRemainStep = remainStep - 1
                    targetMaxRemainStep = maxRemainStep
                    targetIsTurn = isTurn
                } else {
                    if (!isTurn) {
                        targetMaxRemainStep = maxRemainStep
                        targetIsTurn = true
                    } else {
                        targetMaxRemainStep = maxRemainStep + 1
                        targetIsTurn = false
                    }
                    targetNextDirection = (newNextDirection + 1) % 4
                    targetNewRow = row + dxAndDy[targetNextDirection].second
                    targetNewCol = col + dxAndDy[targetNextDirection].first
                    targetRemainStep = targetMaxRemainStep - 1
                }
            }
        }
    }

    if (targetNewRow != -9999 && targetNewCol != -9999) {
        makeStorm(
            cur + 1,
            targetNewRow,
            targetNewCol,
            targetNextDirection,
            targetRemainStep,
            targetMaxRemainStep,
            targetIsTurn
        )
    }

}

fun isValidRowAndCol(row: Int, col: Int) =
    row >= -5010 && row <= 5010 && col >= -5010 && col <= 5010