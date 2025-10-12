package template.graph

/**
 * ✅ 크루스칼 MST
 * - 간선을 가중치 오름차순으로 정렬 → 사이클이 생기지 않을 때만 채택
 * - Union-Find 활용
 */
data class EdgeW(val u: Int, val v: Int, val w: Long)
fun kruskal(n: Int, edges: List<EdgeW>): Pair<Long, List<EdgeW>> {
    val uf = UnionFind(n)
    val sorted = edges.sortedBy { it.w }
    var cost = 0L
    val picked = ArrayList<EdgeW>()
    for (e in sorted) {
        if (uf.unite(e.u, e.v)) {
            cost += e.w; picked.add(e)
            if (picked.size == n - 1) break
        }
    }
    return cost to picked
}
