package codeforces.`2025-09-16`.b_maximum_cost_permutation

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val t = scanner.nextInt()
    val solution = Solution()
    repeat(t) {
        val n = scanner.nextInt()
        val p = IntArray(n) { scanner.nextInt() }
        println(solution.solve(n, p))
    }
}

class Solution {
    fun solve(n: Int, p: IntArray): Int {

        // 1단계: 누락된 숫자들 찾기
        val missing = mutableSetOf<Int>()
        for (i in 1..n) {
            missing.add(i)
        }
        for (i in 0 until n) {
            if (p[i] > 0) {
                missing.remove(p[i])
            }
        }

        // 2단계: 왼쪽 접두사 최소 길이 계산
        var minLeftPrefix = 0
        for (i in 0 until n) {
            if (p[i] == i + 1) {
                // 이미 올바른 위치에 있음
                minLeftPrefix++
            } else if (p[i] == 0) {
                // 0 위치에서 선택 가능
                val neededValue = i + 1
                if (missing.contains(neededValue) && missing.size > 1) {
                    // 다른 선택지가 있으므로 접두사를 끊을 수 있음
                    break
                } else if (missing.contains(neededValue)) {
                    // 이 숫자밖에 선택지가 없음 - 어쩔 수 없이 연장
                    minLeftPrefix++
                    missing.remove(neededValue)
                } else {
                    // 이 위치에 올바른 숫자가 없음 - 접두사 끊어짐
                    break
                }
            } else {
                // 잘못된 위치에 있음 - 접두사 끊어짐
                break
            }
        }

        // 3단계: 오른쪽 접미사 최소 길이 계산 (남은 숫자들로)
        var minRightSuffix = 0
        val remainingMissing = missing.toMutableSet()

        for (i in n - 1 downTo 0) {
            if (p[i] == i + 1) {
                // 이미 올바른 위치에 있음
                minRightSuffix++
            } else if (p[i] == 0) {
                // 0 위치에서 선택 가능
                val neededValue = i + 1
                if (remainingMissing.contains(neededValue) && remainingMissing.size > 1) {
                    // 다른 선택지가 있으므로 접미사를 끊을 수 있음
                    break
                } else if (remainingMissing.contains(neededValue)) {
                    // 이 숫자밖에 선택지가 없음 - 어쩔 수 없이 연장
                    minRightSuffix++
                    remainingMissing.remove(neededValue)
                } else {
                    // 이 위치에 올바른 숫자가 없음 - 접미사 끊어짐
                    break
                }
            } else {
                // 잘못된 위치에 있음 - 접미사 끊어짐
                break
            }
        }

        // 4단계: 겹침 처리
        if (minLeftPrefix + minRightSuffix >= n) {
            return 0
        }

        // 최대 비용 계산
        return n - minLeftPrefix - minRightSuffix
    }
}