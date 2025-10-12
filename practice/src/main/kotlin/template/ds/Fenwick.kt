package template.ds

/**
 * ✅ 펜윅 트리(Fenwick, BIT) - 0-indexed 버전
 * - 점 업데이트, prefix 합 쿼리를 O(log N)에 처리
 * - 구간 합 [l..r] = sum(r) - sum(l-1)
 */
class Fenwick(private val n: Int) {
    private val bit = LongArray(n + 1)
    fun add(idx0: Int, delta: Long) {
        var i = idx0 + 1
        while (i <= n) { bit[i] += delta; i += i and -i }
    }
    fun sum(idx0: Int): Long { // sum [0..idx0]
        var i = idx0 + 1
        var s = 0L
        while (i > 0) { s += bit[i]; i -= i and -i }
        return s
    }
    fun rangeSum(l: Int, r: Int): Long = if (l > r) 0 else sum(r) - if (l == 0) 0 else sum(l - 1)
}
