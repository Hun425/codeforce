package template.flow

import java.util.ArrayDeque

/**
 * ✅ Hopcroft–Karp (이분 매칭 최대 매칭)
 * - BFS로 레벨 그래프 구성 → DFS로 증가 경로를 병렬로 찾기
 * - O(E * sqrt(V))
 */
class HopcroftKarp(private val nL: Int, private val nR: Int) {
    private val adj = Array(nL) { mutableListOf<Int>() }
    private val dist = IntArray(nL + 1)
    private val pairU = IntArray(nL) { -1 }
    private val pairV = IntArray(nR) { -1 }
    private val NIL = nL

    fun addEdge(u: Int, v: Int) { adj[u].add(v) }

    private fun bfs(): Boolean {
        val q: ArrayDeque<Int> = ArrayDeque()
        for (u in 0 until nL) {
            if (pairU[u] == -1) { dist[u] = 0; q.add(u) } else dist[u] = Int.MAX_VALUE
        }
        var found = false
        while (q.isNotEmpty()) {
            val u = q.removeFirst()
            for (v in adj[u]) {
                val pu = pairV[v]
                if (pu == -1) found = true
                else if (dist[pu] == Int.MAX_VALUE) { dist[pu] = dist[u] + 1; q.add(pu) }
            }
        }
        return found
    }

    private fun dfs(u: Int): Boolean {
        for (v in adj[u]) {
            val pu = pairV[v]
            if (pu == -1 || (dist[pu] == dist[u] + 1 && dfs(pu))) {
                pairU[u] = v; pairV[v] = u
                return true
            }
        }
        dist[u] = Int.MAX_VALUE
        return false
    }

    fun maxMatching(): Int {
        var matching = 0
        while (bfs()) for (u in 0 until nL) if (pairU[u] == -1 && dfs(u)) matching++
        return matching
    }
}
