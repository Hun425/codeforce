package template.graph

import java.util.ArrayDeque

/**
 * ✅ 일반 그래프 BFS (0-indexed 인접 리스트)
 * - 간선 가중치가 없는 그래프에서 최단 간선 수를 구합니다.
 * - dist[v] = 시작점에서 v까지의 최단 간선 수 (도달 불가: -1)
 */
fun bfsGraph(adj: Array<MutableList<Int>>, s: Int): IntArray {
    val n = adj.size
    val dist = IntArray(n) { -1 }
    val q: ArrayDeque<Int> = ArrayDeque()
    dist[s] = 0
    q.add(s)
    while (q.isNotEmpty()) {
        val v = q.removeFirst()
        for (to in adj[v]) {
            if (dist[to] == -1) {
                dist[to] = dist[v] + 1
                q.add(to)
            }
        }
    }
    return dist
}
