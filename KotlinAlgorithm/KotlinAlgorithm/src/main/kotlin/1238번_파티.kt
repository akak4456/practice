import java.util.*

val INF = 987654321
val fromXD: Array<Int> = Array(1000 + 1) {
    INF
} // X 에서 모든 정점으로 가는데 걸리는 최소 비용

val toXD: Array<Int> = Array(1000 + 1) {
    INF
} // 모든 정점에서 X 로 가는데 걸리는 최소 비용

val fromXGraph: Array<MutableList<Pair<Int, Int>>> = Array(1000 + 1) {
    mutableListOf()
} // X 에서 모든 정점으로 가는데 걸리는 최소 비용을 구하는데 쓰이는 그래프
// Pair.first = 노드, Pair.second = 해당 노드로 가는데 걸리는 시간

val toXGraph: Array<MutableList<Pair<Int, Int>>> = Array(1000 + 1) {
    mutableListOf()
} // 모든 정점에서 X로 가는데 걸리는 최소 비용을 구하는데 쓰이는 그래프 fromXGraph 의 간선들을 뒤집은 형태
var N = 0
var M = 0
var X = 0

fun dijkstra(d: Array<Int>, graph: Array<MutableList<Pair<Int, Int>>>) {
    val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
    pq.add(Pair(0, X))
    d[X] = 0
    while (pq.isNotEmpty()) {
        val element = pq.remove()
        val time = element.first
        val startNode = element.second
        if (d[startNode] < time) continue
        for (near in graph[startNode]) {
            if (time + near.second < d[near.first]) {
                d[near.first] = time + near.second
                pq.add(Pair(d[near.first], near.first))
            }
        }
    }
}

fun main(args: Array<String>) = with(Scanner(System.`in`)) {
    N = nextInt()
    M = nextInt()
    X = nextInt()
    for (i in 0 until M) {
        val start = nextInt()
        val end = nextInt()
        val time = nextInt()
        fromXGraph[start].add(Pair(end, time))
        toXGraph[end].add(Pair(start, time))
    }
    dijkstra(fromXD, fromXGraph)
    dijkstra(toXD, toXGraph)
    var solution = 0
    for (idx in 1..N) {
        if (solution < fromXD[idx] + toXD[idx]) {
            solution = fromXD[idx] + toXD[idx]
        }
    }
    println(solution)
}