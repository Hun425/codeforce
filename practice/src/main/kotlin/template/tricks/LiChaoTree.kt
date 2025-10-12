package template.tricks

/**
 * ✅ Li Chao Tree (동적 Convex Hull Trick) – 최소값 쿼리
 * - 선형함수 f(x) = m*x + b 를 동적으로 추가하고, 임의의 x에서의 최소 f(x)를 질의
 * - xs: 질의로 등장하는 x좌표들을 미리 정렬한 배열(좌표압축 단서)
 */
class LiChaoTree(private val xs: LongArray) {
    data class Line(val m: Long, val b: Long) { fun f(x: Long) = m * x + b }
    data class Node(var ln: Line?, var l: Int, var r: Int, var left: Node?, var right: Node?)

    private val root = Node(null, 0, xs.size - 1, null, null)

    fun addLine(line: Line) = addLine(root, line)

    private fun addLine(node: Node, line: Line) {
        val l = node.l; val r = node.r; val m = (l + r) / 2
        val xl = xs[l]; val xm = xs[m]; val xr = xs[r]

        var lo = node.ln; var hi = line
        if (lo == null) { node.ln = line; return }
        fun better(a: Line, b: Line, x: Long) = a.f(x) < b.f(x)

        // 항상 lo가 (l, m, r) 구간에서 더 좋은 선이 되도록 swap
        if (better(hi, lo, xl)) { val t = lo; lo = hi; hi = t }
        if (better(hi!!, lo!!, xr)) {
            node.ln = hi
            if (l == r) return
            if (node.right == null) node.right = Node(null, m + 1, r, null, null)
            addLine(node.right!!, lo)
        } else {
            node.ln = lo
            if (l == r) return
            if (node.left == null) node.left = Node(null, l, m, null, null)
            addLine(node.left!!, hi)
        }
    }

    fun query(x: Long): Long = query(root, x)

    private fun query(node: Node?, x: Long): Long {
        if (node == null) return Long.MAX_VALUE / 4
        val m = (node.l + node.r) / 2
        val xm = xs[m]
        val cur = node.ln?.f(x) ?: Long.MAX_VALUE / 4
        return if (x <= xm) minOf(cur, query(node.left, x)) else minOf(cur, query(node.right, x))
    }
}
