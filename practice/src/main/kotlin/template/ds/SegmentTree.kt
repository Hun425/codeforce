package template.ds

/**
 * ✅ 세그트리(합) - 점 갱신 / 구간 합
 * - 트리 크기: 4N 관례 사용
 * - 0-indexed 배열을 [0..n-1] 범위로 처리
 */
class SegmentTree(private val n: Int) {
    private val t = LongArray(4 * n)

    private fun build(node: Int, l: Int, r: Int, a: LongArray) {
        if (l == r) { t[node] = a[l]; return }
        val m = (l + r) / 2
        build(node*2, l, m, a); build(node*2+1, m+1, r, a)
        t[node] = t[node*2] + t[node*2+1]
    }
    fun build(a: LongArray) = build(1, 0, n-1, a)

    private fun update(node: Int, l: Int, r: Int, idx: Int, v: Long) {
        if (l == r) { t[node] = v; return }
        val m = (l + r) / 2
        if (idx <= m) update(node*2, l, m, idx, v) else update(node*2+1, m+1, r, idx, v)
        t[node] = t[node*2] + t[node*2+1]
    }
    fun update(idx: Int, v: Long) = update(1, 0, n-1, idx, v)

    private fun query(node: Int, l: Int, r: Int, ql: Int, qr: Int): Long {
        if (qr < l || r < ql) return 0
        if (ql <= l && r <= qr) return t[node]
        val m = (l + r) / 2
        return query(node*2, l, m, ql, qr) + query(node*2+1, m+1, r, ql, qr)
    }
    fun query(l: Int, r: Int) = query(1, 0, n-1, l, r)
}
