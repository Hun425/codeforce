package template.graph

import java.util.PriorityQueue

/**
 * ✅ 프림 MST (인접 리스트)
 * - 방문하지 않은 정점으로 나가는 최소 간선을 매번 선택
 * - 우선순위 큐 사용
 */
data class WEdge(val to: Int, val w: Long)
fun primMST(adj: Array<MutableList<WEdge>>, s: Int = 0): Long {
    val n = adj.size
    val vis = BooleanArray(n)
    val pq = PriorityQueue(compareBy<Pair<Long,Int>> { it.first })
    pq.add(0L to s)
    var total = 0L
    while (pq.isNotEmpty()) {
        val (w, v) = pq.poll()
        if (vis[v]) continue
        vis[v] = true
        total += w
        for (e in adj[v]) if (!vis[e.to]) pq.add(e.w to e.to)
    }
    return total
}
