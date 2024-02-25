import java.util.*

const val MAX_N = 4000000
val isPrime = Array<Boolean>(MAX_N + 1) {
    true
}
val primes: MutableList<Int> = mutableListOf()
fun main(args: Array<String>) = with(Scanner(System.`in`)) {
    val N = nextInt()
    isPrime[0] = false
    isPrime[1] = false
    for(idx in 2..MAX_N) {
        if(isPrime[idx]) {
            var notPrimeNumber = idx + idx
            while(notPrimeNumber <= MAX_N) {
                isPrime[notPrimeNumber] = false
                notPrimeNumber += idx
            }
        }
    }

    for(idx in 0..MAX_N) {
        if(isPrime[idx]) {
            primes.add(idx)
        }
    }

    var startPos = 0
    var endPos = 0
    var sum = 0
    var ans = 0
    while (endPos <= primes.size && startPos <= endPos) {
        if(sum < N) {
            if(endPos < primes.size) {
                sum += primes[endPos]
            }
            endPos++
        } else if(sum == N) {
            ans++
            sum -= primes[startPos]
            startPos++
        } else {
            sum -= primes[startPos]
            startPos++
        }
    }
    println(ans)
}