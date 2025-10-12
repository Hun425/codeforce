package template.string

/**
 * ✅ 접미사 배열 + Kasai LCP
 * - 정렬 기준: (현재 등급, k칸 뒤 등급)
 * - 등급이 더 이상 변하지 않으면 종료
 */
fun suffixArray(s: String): IntArray {
    val n = s.length
    var sa = IntArray(n) { it }
    var rnk = IntArray(n) { s[it].code }
    var tmp = IntArray(n)

    var k = 1
    while (k < n) {
        sa.sortWith(compareBy({ rnk[it] }, { if (it + k < n) rnk[it + k] else -1 }))
        tmp[sa[0]] = 0
        for (i in 1 until n) {
            val a = sa[i-1]; val b = sa[i]
            tmp[b] = tmp[a] + if (rnk[a] != rnk[b] || (a + k < n && b + k < n && rnk[a + k] != rnk[b + k]) || ((a + k >= n) != (b + k >= n))) 1 else 0
        }
        rnk = tmp.also { tmp = rnk }
        if (rnk[sa[n-1]] == n - 1) break
        k = k shl 1
    }
    return sa
}

fun lcpArray(s: String, sa: IntArray): IntArray {
    val n = s.length
    val rnk = IntArray(n)
    for (i in 0 until n) rnk[sa[i]] = i
    val lcp = IntArray(n - 1)
    var k = 0
    for (i in 0 until n) {
        val r = rnk[i]
        if (r == n - 1) { k = 0; continue }
        val j = sa[r + 1]
        while (i + k < n && j + k < n && s[i + k] == s[j + k]) k++
        lcp[r] = k
        if (k > 0) k--
    }
    return lcp
}
