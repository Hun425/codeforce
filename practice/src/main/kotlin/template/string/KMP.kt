package template.string

/**
 * ✅ KMP (접두사 함수 기반)
 * - pi[i] = s[0..i] 접두사와 s의 접미사가 일치하는 최대 길이
 * - 검색 시 불일치 발생하면 pi를 이용해 점프
 */
fun prefixFunction(s: String): IntArray {
    val n = s.length
    val pi = IntArray(n)
    for (i in 1 until n) {
        var j = pi[i-1]
        while (j > 0 && s[i] != s[j]) j = pi[j-1]
        if (s[i] == s[j]) j++
        pi[i] = j
    }
    return pi
}

fun kmpSearch(text: String, pat: String): List<Int> {
    val pi = prefixFunction(pat)
    val res = ArrayList<Int>()
    var j = 0
    for (i in text.indices) {
        while (j > 0 && text[i] != pat[j]) j = pi[j-1]
        if (text[i] == pat[j]) j++
        if (j == pat.length) {
            res.add(i - j + 1) // 일치 시작 인덱스
            j = pi[j-1]
        }
    }
    return res
}
