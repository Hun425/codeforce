package template.flow

import java.util.ArrayDeque
import kotlin.math.min

/**
 * ✅ 최소 비용 최대 유량 (SPFA 기반 예시)
 * - 음수 간선이 있을 수 있어 SPFA를 사용 (성능 이슈가 있으면 잠재치(potential) + 다익스트라로 개선)
 * - (flow, cost) 반환
 */
class MinCostMaxFlow(private val n: Int) {
    data class E(var to: Int, var cap: Int, var cost: Int, var rev: Int)
    private val g = Array(n) { ArrayList<E>() }

    fun addEdge(u: Int, v: Int, cap: Int, cost: Int) {
        val a = E(v, cap, cost, g[v].size)
        val b = E(u, 0, -cost, g[u].size)
        g[u].add(a); g[v].add(b)
    }

    fun minCostMaxFlow(s: Int, t: Int): Pair<Int, Long> {
        var flow = 0
        var cost = 0L
        val INF = 1e15.toLong()
        val dist = LongArray(n)
        val pv = IntArray(n)
        val pe = IntArray(n)
        val inq = BooleanArray(n)
        while (true) {
            java.util.Arrays.fill(dist, INF)
            java.util.Arrays.fill(inq, false)
            dist[s] = 0
            val q: ArrayDeque<Int> = ArrayDeque()
            q.add(s); inq[s] = true
            while (q.isNotEmpty()) {
                val v = q.removeFirst(); inq[v] = false
                for ((i, e) in g[v].withIndex()) {
                    if (e.cap > 0 && dist[v] + e.cost < dist[e.to]) {
                        dist[e.to] = dist[v] + e.cost
                        pv[e.to] = v; pe[e.to] = i
                        if (!inq[e.to]) { inq[e.to] = true; q.add(e.to) }
                    }
                }
            }
            if (dist[t] == INF) break
            var add = Int.MAX_VALUE
            var v = t
            while (v != s) {
                val e = g[pv[v]][pe[v]]
                add = min(add, e.cap); v = pv[v]
            }
            v = t
            while (v != s) {
                val e = g[pv[v]][pe[v]]
                e.cap -= add
                g[v][e.rev].cap += add
                v = pv[v]
            }
            flow += add
            cost += dist[t] * add
        }
        return flow to cost
    }
}
