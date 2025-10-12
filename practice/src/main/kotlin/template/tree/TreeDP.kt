package template.tree

/**
 * ✅ 트리 DP 기본 뼈대
 * - parent/depth/subSize 전처리
 * - 문제에 맞게 DP 테이블만 추가하세요.
 */
class TreeDP(private val n: Int, private val adj: Array<MutableList<Int>>) {
    val subSize = IntArray(n) { 1 }
    val parent = IntArray(n) { -1 }
    val depth = IntArray(n)

    fun dfs(root: Int = 0) {
        fun go(v: Int, p: Int) {
            parent[v] = p
            for (to in adj[v]) if (to != p) {
                depth[to] = depth[v] + 1
                go(to, v)
                subSize[v] += subSize[to]
            }
        }
        go(root, -1)
    }
}
