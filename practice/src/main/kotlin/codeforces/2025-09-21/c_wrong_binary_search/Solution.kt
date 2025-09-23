package codeforces.`2025-09-24`.c_wrong_binary_search

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

private fun buildPermutationOrNull(n: Int, s: String): IntArray? {
    val p = IntArray(n)
    var i = 0
    while (i < n) {
        if (s[i] == '1') {
            p[i] = i + 1
            i++
        } else {
            var j = i
            while (j < n && s[j] == '0') j++
            val len = j - i
            if (len == 1) return null
            // [i..j-1] 구간을 원형 시프트: i..j-2 -> +1, j-1 -> i
            for (k in i until j - 1) p[k] = (k + 1) + 1
            p[j - 1] = i + 1
            i = j
        }
    }
    return p
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val out = StringBuilder()
    val t = br.readLine().trim().toInt()
    repeat(t) {
        val n = br.readLine().trim().toInt()
        val s = br.readLine().trim()
        val p = buildPermutationOrNull(n, s)
        if (p == null) {
            out.append("NO\n")
        } else {
            out.append("YES\n")
            out.append(p.joinToString(" ")).append('\n')
        }
    }
    print(out.toString())
}
