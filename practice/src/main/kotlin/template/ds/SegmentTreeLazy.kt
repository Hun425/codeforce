package template.ds

/**
 * ✅ Lazy 세그트리 - 구간 추가 / 구간 합
 * - lz[node]에 미루어진 델타를 저장하여 자식으로 필요할 때만 전파(push)
 *
 * 🔎 패턴
 * - rangeAdd: 완전히 포함되면 apply 호출, 아니면 push 후 좌/우 재귀
 * - rangeSum: 질의 전 push 호출로 값의 일관성 유지
 */
class SegmentTreeLazy(private val n: Int) {
    private val t = LongArray(4 * n)
    private val lz = LongArray(4 * n)

    private fun apply(node: Int, l: Int, r: Int, v: Long) {
        t[node] += v * (r - l + 1)
        lz[node] += v
    }
    private fun push(node: Int, l: Int, r: Int) {
        if (lz[node] != 0L) {
            val m = (l + r) / 2
            apply(node*2, l, m, lz[node])
            apply(node*2+1, m+1, r, lz[node])
            lz[node] = 0L
        }
    }

    private fun build(node: Int, l: Int, r: Int, a: LongArray) {
        if (l == r) { t[node] = a[l]; return }
        val m = (l + r) / 2
        build(node*2, l, m, a); build(node*2+1, m+1, r, a)
        t[node] = t[node*2] + t[node*2+1]
    }
    fun build(a: LongArray) = build(1, 0, n-1, a)

    private fun rangeAdd(node: Int, l: Int, r: Int, ql: Int, qr: Int, v: Long) {
        if (qr < l || r < ql) return
        if (ql <= l && r <= qr) { apply(node, l, r, v); return }
        push(node, l, r)
        val m = (l + r) / 2
        rangeAdd(node*2, l, m, ql, qr, v); rangeAdd(node*2+1, m+1, r, ql, qr, v)
        t[node] = t[node*2] + t[node*2+1]
    }
    fun rangeAdd(l: Int, r: Int, v: Long) = rangeAdd(1, 0, n-1, l, r, v)

    private fun rangeSum(node: Int, l: Int, r: Int, ql: Int, qr: Int): Long {
        if (qr < l || r < ql) return 0
        if (ql <= l && r <= qr) return t[node]
        push(node, l, r)
        val m = (l + r) / 2
        return rangeSum(node*2, l, m, ql, qr) + rangeSum(node*2+1, m+1, r, ql, qr)
    }
    fun rangeSum(l: Int, r: Int) = rangeSum(1, 0, n-1, l, r)
}
