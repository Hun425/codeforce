package codeforces.`2025-09-16`.a_cut_the_array

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val t = scanner.nextInt()
    val solution = Solution()
    repeat(t) {
        val n = scanner.nextInt()
        val a = IntArray(n) { scanner.nextInt() }
        val (l, r) = solution.solve(n, a)
        println("$l $r")
    }
}

class Solution {
    fun solve(n: Int, a: IntArray): Pair<Int, Int> {
        val prefixSum = LongArray(n + 1)
        for (i in 0 until n) {
            prefixSum[i + 1] = prefixSum[i] + a[i]
        }

        for (l in 1 until n) {
            for (r in l + 1 until n) {
                val s1 = (prefixSum[l]) % 3
                val s2 = (prefixSum[r] - prefixSum[l]) % 3
                val s3 = (prefixSum[n] - prefixSum[r]) % 3

                val allSame = s1 == s2 && s2 == s3
                val allDifferent = s1 != s2 && s2 != s3 && s1 != s3

                if (allSame || allDifferent) {
                    return Pair(l, r)
                }
            }
        }
        return Pair(0, 0)
    }
}
