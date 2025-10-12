package template.flow

import java.util.ArrayDeque
import kotlin.math.min

/**
 * ✅ Dinic 최대 유량
 * - 레벨 그래프(BFS) 구성 → 레벨을 따라 DFS로 차단 유량(blocking flow) 흘리기
 * - 잔여 용량이 0이 되거나 더 이상 늘릴 수 없을 때까지 반복
 *
 * ⏱️ 복잡도: O(V^2 * E)  (실전에서 매우 빠름)
 */
class Dinic(private val n: Int) {
    data class E(var to: Int, var cap: Long, var rev: Int)
    private val g = Array(n) { ArrayList<E>() }
    private val level = IntArray(n)
    private val it = IntArray(n) // 현재 탐색 포인터(에지 인덱스)

    fun addEdge(u: Int, v: Int, cap: Long) {
        val a = E(v, cap, g[v].size)
        val b = E(u, 0L, g[u].size)
        g[u].add(a); g[v].add(b)
    }

    private fun bfs(s: Int, t: Int): Boolean {
        java.util.Arrays.fill(level, -1)
        val q: ArrayDeque<Int> = ArrayDeque()
        level[s] = 0; q.add(s)
        while (q.isNotEmpty()) {
            val v = q.removeFirst()
            for (e in g[v]) if (e.cap > 0 && level[e.to] < 0) {
                level[e.to] = level[v] + 1
                q.add(e.to)
            }
        }
        return level[t] >= 0
    }

    private fun dfs(v: Int, t: Int, f: Long): Long {
        if (v == t) return f
        var i = it[v]
        while (i < g[v].size) {
            val e = g[v][i]
            if (e.cap > 0 && level[v] < level[e.to]) {
                val d = dfs(e.to, t, min(f, e.cap))
                if (d > 0) {
                    e.cap -= d
                    g[e.to][e.rev].cap += d
                    return d
                }
            }
            i++
            it[v] = i
        }
        return 0
    }

    fun maxFlow(s: Int, t: Int): Long {
        var flow = 0L
        while (bfs(s, t)) {
            java.util.Arrays.fill(it, 0)
            while (true) {
                val f = dfs(s, t, Long.MAX_VALUE / 4)
                if (f == 0L) break
                flow += f
            }
        }
        return flow
    }
}
