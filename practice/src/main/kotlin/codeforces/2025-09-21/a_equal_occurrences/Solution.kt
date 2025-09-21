package codeforces.`2025-09-21`.a_equal_occurrences

private lateinit var currentArray: IntArray

fun main() {
    val t = readln().trim().toInt()
    repeat(t) {
        val n = readln().trim().toInt()
        val a = IntArray(n)
        var got = 0
        while (got < n) {
            val parts = readln().trim().split(" ").filter { it.isNotEmpty() }
            for (p in parts) {
                if (got < n) a[got++] = p.toInt()
            }
        }
        currentArray = a
        println(solve(n, 0)) // y_int는 사용하지 않음
    }
}

fun solve(x_int: Int, y_int: Int): Int {
    val n = x_int
    val a = currentArray
    if (n == 0) return 0

    val freq = IntArray(n + 1)
    var maxF = 0
    for (x in a) {
        freq[x]++
        if (freq[x] > maxF) maxF = freq[x]
    }

    // cntByFreq[f] = "등장 횟수가 정확히 f인 서로 다른 값의 개수"
    val cntByFreq = IntArray(maxF + 1)
    for (v in 1..n) {
        val f = freq[v]
        if (f > 0) cntByFreq[f]++
    }

    var best = 0
    var atLeast = 0 // "등장 횟수가 k 이상인 서로 다른 값의 개수"를 누적 계산
    for (k in maxF downTo 1) {
        atLeast += cntByFreq[k]
        val cand = k * atLeast
        if (cand > best) best = cand
    }
    return best
}
