import java.util.*

fun main(args: Array<String>) = with(Scanner(System.`in`)) {
    val N = nextInt()
    val operand: List<String> = List(N) {
        next()
    }
    val alphabetToTens: MutableList<Long> = MutableList(10) {
        0
    }
    val distinctAlphabets: MutableSet<Int> = mutableSetOf()
    for (str in operand) {
        for (ch in str) {
            distinctAlphabets.add(ch - 'A')
        }
    }
    for (str in operand) {
        var tens = 1L
        for (ch in str.reversed()) {
            alphabetToTens[ch - 'A'] += tens
            tens *= 10
        }
    }
    val alphabetToNumber: Array<Long> = Array(10) {
        -1
    }
    if (distinctAlphabets.size == 10) {
        // 0이 가능한 것 중에서 영향이 제일 작은 것을 먼저 고른다
        val pairs = alphabetToTens.mapIndexed { index, l ->
            Pair(l, index)
        }.sortedBy { it.first }
        for (pair in pairs) {
            var isPossible = true
            for (str in operand) {
                if (str.first() - 'A' == pair.second) {
                    isPossible = false
                    break
                }
            }
            if (isPossible) {
                alphabetToTens[pair.second] = 0
                alphabetToNumber[pair.second] = 0
                break
            }
        }
    }
    for (targetNumber in 9 downTo 1) {
        var maxTarget = -1
        var maxValue = 0L
        for (idx in 0..<10) {
            if (alphabetToTens[idx] > maxValue) {
                maxTarget = idx
                maxValue = alphabetToTens[idx]
            }
        }
        if (maxTarget == -1) {
            break
        }
        alphabetToNumber[maxTarget] = targetNumber.toLong()
        alphabetToTens[maxTarget] = 0
    }

    var sums = 0L

    for (str in operand) {
        var tens = 1L
        for (ch in str.reversed()) {
            sums += alphabetToNumber[ch - 'A'] * tens
            tens *= 10
        }
    }


    println(sums)
}