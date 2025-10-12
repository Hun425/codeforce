package template.tree

/**
 * ✅ LCA (Binary Lifting)
 * - up[k][v] = v에서 2^k만큼 위 조상
 * - 깊이가 더 큰 쪽을 끌어올린 다음, 두 포인터를 동시에 올려서 공통 조상 바로 밑까지 이동
 *
 * ⏱️ 전처리 O(N log N), 질의 O(log N)
 */
class LCA(n: Int, private val root: Int, adj: Array<MutableList<Int>>) {
    private val LOG = 20
    private val up = Array(LOG) { IntArray(n) { -1 } }
    private val depth = IntArray(n)

    init {
        fun dfs(v: Int, p: Int) {
            up[0][v] = p
            for (to in adj[v]) if (to != p) {
                depth[to] = depth[v] + 1
                dfs(to, v)
            }
        }
        dfs(root, -1)
        // 점화식: up[k][v] = up[k-1][ up[k-1][v] ]
        for (k in 1 until LOG) {
            for (v in 0 until n) {
                val mid = up[k-1][v]
                up[k][v] = if (mid == -1) -1 else up[k-1][mid]
            }
        }
    }

    fun lca(a0: Int, b0: Int): Int {
        var a = a0; var b = b0
        // 깊이 보정: a가 더 깊도록 강제
        if (depth[a] < depth[b]) a = b.also { b = a }
        var diff = depth[a] - depth[b]
        for (k in 0 until LOG) if ((diff and (1 shl k)) != 0) a = up[k][a]
        if (a == b) return a
        // 공통 조상 바로 밑까지 올리기
        for (k in LOG - 1 downTo 0) {
            if (up[k][a] != up[k][b]) { a = up[k][a]; b = up[k][b] }
        }
        return up[0][a]
    }
}
