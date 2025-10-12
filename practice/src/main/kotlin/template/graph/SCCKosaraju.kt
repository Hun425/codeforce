package template.graph

/**
 * ✅ 강결합 요소(SCC) - Kosaraju 알고리즘
 * 1) 원그래프에서 DFS 후 나가는 순서(완료 시간) 역순 스택 확보
 * 2) 간선 방향을 뒤집은 그래프에서 스택 역순으로 DFS → 컴포넌트 얻기
 */
fun sccKosaraju(adj: Array<MutableList<Int>>): Pair<IntArray, Int> {
    val n = adj.size
    val radj = Array(n) { mutableListOf<Int>() }
    for (i in 0 until n) for (to in adj[i]) radj[to].add(i)

    val used = BooleanArray(n)
    val order = ArrayList<Int>()
    fun dfs1(v: Int) {
        used[v] = true
        for (to in adj[v]) if (!used[to]) dfs1(to)
        order.add(v) // 종료 순서 기록
    }
    for (i in 0 until n) if (!used[i]) dfs1(i)

    val comp = IntArray(n) { -1 }
    var j = 0
    fun dfs2(v: Int) {
        comp[v] = j
        for (to in radj[v]) if (comp[to] == -1) dfs2(to)
    }
    for (i in order.size - 1 downTo 0) {
        val v = order[i]
        if (comp[v] == -1) { dfs2(v); j++ }
    }
    return comp to j // comp[v] = 컴포넌트 ID, j = 컴포넌트 개수
}
