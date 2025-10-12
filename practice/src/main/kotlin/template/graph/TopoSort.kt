package template.graph

import java.util.ArrayDeque

/**
 * ✅ 위상 정렬 (Kahn 알고리즘)
 * - 방향 비순환 그래프(DAG)에서 진입차수 0인 정점부터 꺼내며 순서를 만듦
 * - 싸이클 존재 시 모든 정점이 출력되지 않음 → 크기 체크로 검출 가능
 */
fun topoSort(adj: Array<MutableList<Int>>): List<Int> {
    val n = adj.size
    val indeg = IntArray(n)
    for (v in 0 until n) for (to in adj[v]) indeg[to]++
    val q: ArrayDeque<Int> = ArrayDeque()
    for (i in 0 until n) if (indeg[i] == 0) q.add(i)
    val order = ArrayList<Int>()
    while (q.isNotEmpty()) {
        val v = q.removeFirst()
        order.add(v)
        for (to in adj[v]) {
            if (--indeg[to] == 0) q.add(to)
        }
    }
    return order
}
