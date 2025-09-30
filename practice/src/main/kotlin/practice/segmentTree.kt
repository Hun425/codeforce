package practice

class segmentTree(
    private val n: Int,
    arr: LongArray
) {
    private val tree = LongArray(4 * n)
    private val lazy = LongArray(4 * n)

    init {
        build(1, 0, n, arr)
    }

    private fun build(node: Int, l: Int, r: Int, a: LongArray) {
        if (r - l == 1) {
            tree[node] = if (l < a.size) a[l] else 0L
            return
        }

        val m = (l + r) / 2

        build(node * 2, l, m, a)
        build(node * 2 + 1, m, r, a)

        tree[node] = tree[node * 2] + tree[node * 2 + 1]
    }

    //[ql, qr) 구간에 +
    fun rangeAdd(ql: Int, qr: Int, v: Long) = rangeAdd(1, 0, n, ql, qr, v)

    private fun rangeAdd(node: Int, l: Int, r: Int, ql: Int, qr: Int, v: Long) {
        if (qr <= l || r <= ql) return
        if (ql <= l && r <= qr) {
            apply(node, l, r, v); return
        }

        push(node, l, r)

        val m = (l + r) / 2

        rangeAdd(node * 2, l, m, ql, qr, v)
        rangeAdd(node * 2 + 1, m, r, ql, qr, v)

        tree[node] = tree[node * 2] + tree[node * 2 + 1]
    }

    private fun apply(node: Int, l: Int, r: Int, add: Long) {
        tree[node] += add * (r - l)
        lazy[node] += add
    }

    private fun push(node: Int, l: Int, r: Int) {

        val tag = lazy[node]

        if (tag == 0L || r - l == 1) return

        val m = (l + r) / 2

        apply(node * 2, l, m, tag)
        apply(node * 2 + 1, m, r, tag)

        lazy[node] = 0L
    }

    fun rangeSum(ql:Int, qr:Int) = rangeSum(1, 0, n, ql, qr)

    private fun rangeSum(node: Int, l: Int, r: Int, ql: Int, qr: Int): Long {
        if (qr <= l || r <= ql) return 0L
        if (ql <= l && r <= qr) return tree[node]

        push(node, l, r)

        val m = (l + r) / 2
        val left = rangeSum(node * 2, l, m, ql, qr)
        val right = rangeSum(node * 2 + 1, m, r, ql, qr)
        return left + right
    }

    fun rangeAddClosed(L: Int, R: Int, v: Long) = rangeAdd(L, R + 1, v)
    fun rangeSumClosed(L: Int, R: Int): Long = rangeSum(L, R + 1)



}

fun main() {
    val arr = longArrayOf(2, 1, 3, 5, 4, 6)
    val st = segmentTree(arr.size, arr)
    st.rangeAddClosed(1, 3, 2)             // [1,3]에 +2
    println(st.rangeSumClosed(0, 3))       // 기대 17
    println(st.rangeSumClosed(2, 5))       // 기대 16
}
