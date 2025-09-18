package codeforces.`2025-09-17`.c_max_tree

import java.util.*

fun main() {
    val t = try {
        readln().toInt()
    } catch (e: Exception) {
        1
    }
    repeat(t) {
        solve()
    }
}

fun solve() {
    // 정점의 수를 읽습니다.
    val n = readln().toInt()

    // 제약 조건에 따른 방향 그래프를 표현하기 위한 인접 리스트입니다.
    val adj = Array(n + 1) { mutableListOf<Int>() }
    // 각 정점의 진입 차수를 저장하는 배열입니다.
    val inDegree = IntArray(n + 1)

    // n-1개의 간선을 읽고 제약 조건 그래프를 구축합니다.
    repeat(n - 1) {
        val (u, v, x, y) = readln().split(" ").map { it.toInt() }
        
        // 합을 최대화하기 위해 각 간선의 기여도로 x와 y 중 더 큰 값을 선택합니다.
        // 만약 x > y 이면, p[u] > p[v] 관계를 만족해야 합니다. 이는 위상 정렬에서 v가 u보다 먼저 나와야 함을 의미하는
        // v -> u 의존성을 생성합니다.
        if (x > y) {
            adj[v].add(u)
            inDegree[u]++
        } else {
            // 만약 y >= x 이면, p[v] > p[u] 관계를 만족해야 합니다. 이는 u가 v보다 먼저 나와야 함을 의미하는
            // u -> v 의존성을 생성합니다.
            adj[u].add(v)
            inDegree[v]++
        }
    }

    // 칸 알고리즘(위상 정렬)을 위한 큐를 초기화합니다.
    // 진입 차수가 0인 모든 정점을 큐에 추가합니다.
    val queue: Queue<Int> = LinkedList()
    for (i in 1..n) {
        if (inDegree[i] == 0) {
            queue.add(i)
        }
    }

    // 정점을 위상 정렬 순서로 저장할 리스트입니다.
    val topoOrder = mutableListOf<Int>()
    
    // 칸 알고리즘을 사용하여 그래프를 처리합니다.
    while (queue.isNotEmpty()) {
        val u = queue.poll()
        topoOrder.add(u)

        // 현재 정점의 각 이웃에 대해 진입 차수를 감소시킵니다.
        // 이웃의 진입 차수가 0이 되면 큐에 추가합니다.
        for (v in adj[u]) {
            inDegree[v]--
            if (inDegree[v] == 0) {
                queue.add(v)
            }
        }
    }

    // 결과 순열을 저장할 배열입니다.
    val p = IntArray(n + 1)
    
    // 위상 정렬 순서에 따라 순열 값을 할당합니다.
    // 위상 정렬에서 먼저 나오는 정점일수록 더 작은 순열 값을 가집니다.
    for (i in 0 until n) {
        p[topoOrder[i]] = i + 1
    }

    // 결과 순열을 출력합니다.
    println(p.slice(1..n).joinToString(" "))
}
