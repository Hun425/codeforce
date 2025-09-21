package codeforces.`2025-09-20`.b_multiple_construction

fun main() {
    val t = readln().toInt()
    val sb = StringBuilder()
    repeat(t) {
        val n = readln().toInt()
        sb.append(solve(n)).append("\n")
    }
    print(sb.toString())
}

fun solve(n: Int): String {
    val N = 2 * n
    val ans = IntArray(N) { 0 }
    val parent = IntArray(N + 1) { it }

    // 유니온 파인드
    fun findStart(i0: Int): Int {
        var i = i0
        while (parent[i] != i) {
            parent[i] = parent[parent[i]]
            i = parent[i]
        }
        return i
    }

    // 그룹 합쳐버림
    fun removeIdx(i: Int) {
        parent[i] = findStart(i + 1)
    }

    for (x in n downTo 1) {
        var p = findStart(0)
        var placed = false
        while (p < N) {
            val maxK = (N - 1 - p) / x
            var k = 1
            while (k <= maxK) {
                val qPos = p + k * x
                val q = findStart(qPos)
                if (q == qPos && ans[p] == 0 && ans[q] == 0) {
                    ans[p] = x
                    ans[q] = x
                    removeIdx(p)
                    removeIdx(q)
                    placed = true
                    break
                }
                k++
            }
            if (placed) break
            p = findStart(p + 1)
        }
    }

    return ans.joinToString(" ")
}
