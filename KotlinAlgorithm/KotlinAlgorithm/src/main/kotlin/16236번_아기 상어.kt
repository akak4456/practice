import java.util.*
import kotlin.collections.ArrayDeque

fun main(args: Array<String>) = with(Scanner(System.`in`)) {
    val N = nextInt()
    val board: MutableList<MutableList<Int>> = MutableList(N) {
        MutableList(N) {
            nextInt()
        }
    }
    var result = 0
    var sharkSize = 2
    var eatFishCount = 0
    while (true) {
        val way = findWay(board, sharkSize)
        if (way != null) {
            eatFishCount++
            if (eatFishCount == sharkSize) {
                eatFishCount = 0
                sharkSize++
            }
            result += way.first
            for (rowIdx in 0..<N) {
                for (colIdx in 0..<N) {
                    if (board[rowIdx][colIdx] == 9) {
                        board[rowIdx][colIdx] = 0
                    }
                }
            }
            board[way.second.first][way.second.second] = 9
        } else {
            break
        }
    }
    println(result)
}

fun findWay(originBoard: List<List<Int>>, sharkSize: Int): Pair<Int, Pair<Int, Int>>? {
    var sharkCol = 0
    var sharkRow = 0
    val convertedBoard = originBoard.mapIndexed { rowIdx, row ->
        row.mapIndexed { colIdx, col ->
            if (col == 9) {
                sharkCol = colIdx
                sharkRow = rowIdx
                0
            } else if (col > sharkSize) {
                -1
            } else if (col in 1..<sharkSize) {
                1
            } else {
                0
            }
        }
    }
    val visited: MutableList<MutableList<Boolean>> = MutableList(originBoard.size) {
        MutableList(originBoard.size) {
            false
        }
    }
    val q: ArrayDeque<Pair<Int, Pair<Int, Int>>> = ArrayDeque()
    q.add(Pair(0, Pair(sharkRow, sharkCol)))
    val candidate: MutableList<Pair<Int, Pair<Int, Int>>> = mutableListOf()
    while (q.isNotEmpty()) {
        val target = q.removeFirst()
        val targetTime = target.first
        val targetRow = target.second.first
        val targetCol = target.second.second
        val dRowAndDCol: Array<Pair<Int, Int>> = arrayOf(
            Pair(-1, 0),
            Pair(0, -1),
            Pair(0, 1),
            Pair(1, 0),
        )
        for (d in dRowAndDCol) {
            val newRow = targetRow + d.first
            val newCol = targetCol + d.second
            if (newRow >= 0 && newRow < originBoard.size) {
                if (newCol >= 0 && newCol < originBoard.size) {
                    if (!visited[newRow][newCol]) {
                        if (convertedBoard[newRow][newCol] == 1) {
                            candidate.add(Pair(targetTime + 1, Pair(newRow, newCol)))
                            q.add(Pair(targetTime + 1, Pair(newRow, newCol)))
                            visited[newRow][newCol] = true
                        } else if (convertedBoard[newRow][newCol] == 0) {
                            q.add(Pair(targetTime + 1, Pair(newRow, newCol)))
                            visited[newRow][newCol] = true
                        }
                    }
                }
            }
        }
    }

    if (candidate.isNotEmpty()) {
        return candidate.sortedWith(compareBy({ it.first }, { it.second.first }, { it.second.second })).first()
    }
    return null
}