package template.tree

/**
 * ✅ HLD(Heavy-Light Decomposition)
 * - 경로 쿼리를 구간 쿼리(세그트리 등)로 변환하기 위한 전처리
 *
 * 핵심 아이디어
 * - 각 정점에서 가장 큰 서브트리로 가는 간선을 "heavy"로 지정하여 경로를 O(log N)개 구간으로 분해
 * - head/pos를 이용해 '경로 쿼리'를 '인덱스 구간 쿼리'로 바꿉니다.
 */
class HLD(private val n: Int, private val adj: Array<MutableList<Int>>, private val root: Int = 0) {
    private val parent = IntArray(n) { -1 }
    private val depth = IntArray(n)
    private val heavy = IntArray(n) { -1 }
    private val head = IntArray(n)
    private val pos = IntArray(n)
    private val size = IntArray(n)
    private var curPos = 0

    init {
        fun dfs(v: Int): Int {
            var sz = 1
            var maxSub = 0
            for (to in adj[v]) if (to != parent[v]) {
                parent[to] = v
                depth[to] = depth[v] + 1
                val s = dfs(to)
                if (s > maxSub) { maxSub = s; heavy[v] = to }
                sz += s
            }
            size[v] = sz
            return sz
        }
        dfs(root)

        fun decompose(v: Int, h: Int) {
            head[v] = h
            pos[v] = curPos++
            if (heavy[v] != -1) {
                // heavy 자식은 같은 head를 공유 → 하나의 '굵은 사슬'
                decompose(heavy[v], h)
                // 나머지 자식들은 각각 새로운 head로 시작
                for (to in adj[v]) if (to != parent[v] && to != heavy[v]) decompose(to, to)
            }
        }
        decompose(root, root)
    }

    /**
     * 경로 a-b를 여러 구간으로 나눠서 세그트리 등으로 처리.
     * query(l, r)가 [l..r] 구간을 처리하는 람다라고 가정.
     */
    fun processPath(a0: Int, b0: Int, query: (l: Int, r: Int) -> Long): Long {
        var a = a0; var b = b0
        var res = 0L
        while (head[a] != head[b]) {
            if (depth[head[a]] < depth[head[b]]) { val t = a; a = b; b = t }
            res += query(pos[head[a]], pos[a])
            a = parent[head[a]]
        }
        if (depth[a] > depth[b]) { val t = a; a = b; b = t }
        res += query(pos[a], pos[b])
        return res
    }

    fun position(v: Int) = pos[v]
}
