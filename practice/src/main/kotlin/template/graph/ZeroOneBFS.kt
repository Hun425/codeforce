package template.graph

import java.util.ArrayDeque

/**
 * ✅ 0-1 BFS
 * - 간선 가중치가 {0,1}인 그래프에서 O(N+M)으로 최단경로를 구합니다.
 * - 덱(deque)을 사용하여 가중치 0은 앞, 1은 뒤에 삽입
 */
data class Edge01(val to: Int, val w: Int)

fun zeroOneBFS(adj: Array<MutableList<Edge01>>, s: Int): IntArray {
    val n = adj.size
    val INF = 1e9.toInt()
    val dist = IntArray(n) { INF }
    val dq = ArrayDeque<Int>()
    dist[s] = 0
    dq.addFirst(s)
    while (dq.isNotEmpty()) {
        val v = dq.removeFirst()
        for (e in adj[v]) {
            val nd = dist[v] + e.w
            if (nd < dist[e.to]) {
                dist[e.to] = nd
                if (e.w == 0) dq.addFirst(e.to) else dq.addLast(e.to)
            }
        }
    }
    return dist
}
