package template.graph

import java.util.PriorityQueue

/**
 * ✅ 다익스트라 (비음수 가중치 최단경로)
 * - 인접 리스트에 (to, w)로 간선을 저장합니다.
 * - dist[v]를 갱신할 때마다 (거리, 정점)를 우선순위 큐에 삽입 → 지연 삭제 패턴 사용
 *
 * ⛏️ 포인트
 * - 음수 간선 존재 시 사용할 수 없음
 * - poll한 (d, v)가 현재 dist[v]와 다르면 오래된 항목이므로 skip
 * - prev[]를 두면 경로 복원 가능
 *
 * ⏱️ 복잡도: O((N+M) log N)  (이진 힙)
 */
data class Edge(val to: Int, val w: Long)

fun dijkstra(adj: Array<MutableList<Edge>>, s: Int): LongArray {
    val n = adj.size
    val INF = Long.MAX_VALUE / 4
    val dist = LongArray(n) { INF }
    val pq = PriorityQueue(compareBy<Pair<Long,Int>> { it.first })
    dist[s] = 0L
    pq.add(0L to s)
    while (pq.isNotEmpty()) {
        val (d, v) = pq.poll()
        if (d != dist[v]) continue // 지연 삭제: 오래된 기록은 무시
        for (e in adj[v]) {
            val nd = d + e.w
            if (nd < dist[e.to]) {
                dist[e.to] = nd
                pq.add(nd to e.to)
            }
        }
    }
    return dist
}
