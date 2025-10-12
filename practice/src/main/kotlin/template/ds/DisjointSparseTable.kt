package template.ds

/**
 * ✅ 디스조인트 스파스 테이블 (아무 연산(결합법칙)에도 가능)
 * - O(N log N) 전처리, O(1) 쿼리
 * - 구간이 분할되는 레벨을 기준으로 왼쪽 접두사/오른쪽 접미사를 미리 합쳐둠
 */
class DisjointSparseTable(private val a: LongArray, val op: (Long, Long) -> Long) {
    private val n = a.size
    private val LOG = 32 - Integer.numberOfLeadingZeros(n)
    private val st = Array(LOG) { LongArray(n) }

    init {
        for (i in 0 until n) st[0][i] = a[i]
        var k = 1
        while (k < LOG) {
            val len = 1 shl k
            var i = 0
            while (i < n) {
                val m = minOf(i + (len shr 1), n - 1)

                // 왼쪽: m에서 왼쪽으로 누적
                var acc = a[m]
                for (j in m - 1 downTo i) { acc = op(a[j], acc); st[k][j] = acc }

                // 오른쪽: m+1에서 오른쪽으로 누적
                acc = a[m]
                var j = m + 1
                val end = minOf(i + len, n)
                while (j < end) { acc = op(acc, a[j]); st[k][j] = acc; j++ }

                i += len
            }
            k++
        }
    }

    fun query(l: Int, r: Int): Long {
        if (l == r) return a[l]
        val k = 31 - Integer.numberOfLeadingZeros(l xor r) // l과 r이 처음 갈라지는 레벨
        return op(st[k][l], st[k][r])
    }
}
