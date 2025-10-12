package template.ds

/**
 * ✅ 스파스 테이블 (정답이 멱등인 연산: min/max/gcd 등)
 * - O(N log N) 전처리, O(1) 쿼리
 */
class SparseTable(private val a: IntArray, val op: (Int, Int) -> Int) {
    private val n = a.size
    private val LOG = 32 - Integer.numberOfLeadingZeros(n)
    private val st = Array(LOG) { IntArray(n) }

    init {
        for (i in 0 until n) st[0][i] = a[i]
        var k = 1
        while ((1 shl k) <= n) {
            val len = 1 shl k
            val half = len shr 1
            for (i in 0..n - len) {
                st[k][i] = op(st[k-1][i], st[k-1][i + half])
            }
            k++
        }
    }

    fun query(l: Int, r: Int): Int {
        val len = r - l + 1
        val k = 31 - Integer.numberOfLeadingZeros(len)
        val half = 1 shl k
        return op(st[k][l], st[k][r - half + 1])
    }
}
