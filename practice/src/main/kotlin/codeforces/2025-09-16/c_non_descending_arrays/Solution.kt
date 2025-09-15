package codeforces.`2025-09-16`.c_non_descending_arrays

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val t = scanner.nextInt()
    val solution = Solution()
    repeat(t) {
        val n = scanner.nextInt()
        val a = IntArray(n) { scanner.nextInt() }
        val b = IntArray(n) { scanner.nextInt() }
        println(solution.solve(n, a, b))
    }
}

class Solution {
    private val MOD = 998244353

    fun solve(n: Int, a: IntArray, b: IntArray): Int {
        // 동적 프로그래밍 접근
        // dp[lastA][lastB] = 현재까지 고려했을 때,
        // a의 마지막 값이 lastA, b의 마지막 값이 lastB인 경우의 수

        // 값의 범위가 1~1000이므로 HashMap 사용
        var dp = mutableMapOf<Pair<Int, Int>, Long>()

        // 첫 번째 위치 초기화
        // 교환 안함: (a[0], b[0])
        dp[Pair(a[0], b[0])] = dp.getOrDefault(Pair(a[0], b[0]), 0L) + 1

        // 교환함: (b[0], a[0])
        dp[Pair(b[0], a[0])] = dp.getOrDefault(Pair(b[0], a[0]), 0L) + 1

        // 각 위치별로 DP 진행
        for (i in 1 until n) {
            val newDp = mutableMapOf<Pair<Int, Int>, Long>()

            for ((state, count) in dp) {
                val (lastA, lastB) = state

                // 선택 1: 교환 안함 (a[i], b[i])
                if (a[i] >= lastA && b[i] >= lastB) {
                    val newState = Pair(a[i], b[i])
                    newDp[newState] = (newDp.getOrDefault(newState, 0L) + count) % MOD
                }

                // 선택 2: 교환함 (b[i], a[i])
                if (b[i] >= lastA && a[i] >= lastB) {
                    val newState = Pair(b[i], a[i])
                    newDp[newState] = (newDp.getOrDefault(newState, 0L) + count) % MOD
                }
            }

            dp = newDp
        }

        // 모든 경우의 수 합계
        var result = 0L
        for (count in dp.values) {
            result = (result + count) % MOD
        }

        return result.toInt()
    }
}