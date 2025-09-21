package codeforces.`2025-09-17`.d1_inversion_graph_coloring_easy_version

const val MOD = 1_000_000_007L

class Solution {
    fun solve(n: Int, a: List<Int>): Long {
        // dp[i][j] : 현재까지 처리한 원소들로 만들 수 있는 부분수열(그리디 분배 기준)에 대해
        // pile1의 마지막 값이 i, pile2의 마지막 값이 j 인 경우의 수
        // 값 범위는 0..n (0은 아직 해당 pile에 원소가 없음)
        val dp = Array(n + 1) { LongArray(n + 1) }
        dp[0][0] = 1L

        for (x in a) {
            val newDp = Array(n + 1) { LongArray(n + 1) }

            for (i in 0..n) {
                for (j in 0..n) {
                    val cur = dp[i][j]
                    if (cur == 0L) continue

                    // 1) x 선택 안 함
                    newDp[i][j] = (newDp[i][j] + cur) % MOD

                    // 2) x 선택 — 그리디로 왼쪽부터 집어넣기
                    if (i == 0 || i <= x) {
                        // pile1에 넣음 (pile1이 비어있거나 non-decreasing 유지 가능)
                        newDp[x][j] = (newDp[x][j] + cur) % MOD
                    } else if (j == 0 || j <= x) {
                        // pile1에 못 넣으면 pile2에 넣어본다 (pile2이 비어있거나 non-decreasing 유지 가능)
                        newDp[i][x] = (newDp[i][x] + cur) % MOD
                    }
                    // 둘 다 안 되면 넣을 수 없음 (3번째 감소 필요) -> skip
                }
            }

            // move
            for (i in 0..n) {
                for (j in 0..n) {
                    dp[i][j] = newDp[i][j]
                }
            }
        }

        var ans = 0L
        for (i in 0..n) for (j in 0..n) ans = (ans + dp[i][j]) % MOD
        return ans
    }
}

fun main() {
    val t = readln().toInt()
    val solution = Solution()
    repeat(t) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        println(solution.solve(n, a))
    }
}
