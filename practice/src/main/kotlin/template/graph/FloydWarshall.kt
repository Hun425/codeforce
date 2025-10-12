package template.graph

/**
 * ✅ 플로이드–워셜: 모든 쌍 최단경로 (APSP)
 * - dist[i][j]는 i→j의 최단거리 (도달 불가면 Long.MAX_VALUE 등)
 * - 음수 가중치는 가능하지만, 음수 사이클은 추가 처리 필요
 *
 * ⏱️ 복잡도: O(N^3)
 */
fun floydWarshall(dist: Array<LongArray>) {
    val n = dist.size
    for (k in 0 until n) {
        for (i in 0 until n) {
            val dik = dist[i][k]
            if (dik == Long.MAX_VALUE) continue
            for (j in 0 until n) {
                val nk = dik + dist[k][j]
                if (nk < dist[i][j]) dist[i][j] = nk
            }
        }
    }
}
