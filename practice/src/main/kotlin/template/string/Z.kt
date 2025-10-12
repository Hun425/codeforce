package template.string

/**
 * ✅ Z 알고리즘
 * - z[i] = s와 s[i..]의 LCP 길이
 * - 접두사와 각 접미사의 공통 접두사 길이를 빠르게 얻음
 */
fun zFunction(s: String): IntArray {
    val n = s.length
    val z = IntArray(n)
    var l = 0; var r = 0
    for (i in 1 until n) {
        if (i <= r) z[i] = minOf(r - i + 1, z[i - l])
        while (i + z[i] < n && s[z[i]] == s[i + z[i]]) z[i]++
        if (i + z[i] - 1 > r) { l = i; r = i + z[i] - 1 }
    }
    z[0] = n
    return z
}
