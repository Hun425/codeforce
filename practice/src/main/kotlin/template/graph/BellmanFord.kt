package template.graph

/**
 * ✅ 벨만–포드: 음수 가중치 허용, 음수 사이클 검출
 * - n-1번 완화(relax) 후에도 더 줄어드는 간선이 있으면 음수 사이클
 * - 시작점에서 도달 가능한 음수 사이클만 검출됨을 유의
 *
 * ⏱️ 복잡도: O(N*M)
 */
data class EdgeBF(val u: Int, val v: Int, val w: Long)

fun bellmanFord(n: Int, edges: List<EdgeBF>, s: Int): Pair<LongArray, Boolean> {
    val INF = Long.MAX_VALUE / 4
    val dist = LongArray(n) { INF }
    dist[s] = 0L
    for (i in 0 until n - 1) {
        var any = false
        for ((u, v, w) in edges) {
            if (dist[u] != INF && dist[u] + w < dist[v]) {
                dist[v] = dist[u] + w
                any = true
            }
        }
        if (!any) break // 더 이상 갱신이 없다면 조기 종료
    }
    var negCycle = false
    for ((u, v, w) in edges) {
        if (dist[u] != INF && dist[u] + w < dist[v]) { negCycle = true; break }
    }
    return dist to negCycle
}
