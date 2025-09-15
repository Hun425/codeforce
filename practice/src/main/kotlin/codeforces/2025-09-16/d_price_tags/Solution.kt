package codeforces.`2025-09-16`.d_price_tags

import kotlin.math.*

class Solution {
    fun solve(n: Int, y: Long, prices: IntArray): Long {
        var maxIncome = Long.MIN_VALUE

        // 의미있는 x값들을 더 간단하게 수집
        val candidateXs = mutableSetOf<Int>()

        // 각 가격에 대해 약수들을 기반으로 후보 x 생성
        for (price in prices) {
            for (x in 2..price + 1) {
                candidateXs.add(x)
            }
            // price의 약수들도 추가
            for (div in 1..minOf(price, 1000)) {
                if (price % div == 0) {
                    candidateXs.add(div + 1)
                    if (div > 1) candidateXs.add(div)
                }
            }
        }

        // 추가로 작은 x들도 확인
        for (x in 2..100) {
            candidateXs.add(x)
        }

        // 후보 x들에 대해서만 계산
        for (x in candidateXs.filter { it >= 2 }) {
            val income = calculateIncome(prices, x, y)
            maxIncome = maxOf(maxIncome, income)
        }

        return maxIncome
    }

    private fun calculateIncome(prices: IntArray, x: Int, y: Long): Long {
        // 새로운 가격들 계산 (올림)
        val newPrices = IntArray(prices.size) { (prices[it] + x - 1) / x }

        // 새로운 가격들의 합
        val totalNewPrice = newPrices.sumOf { it.toLong() }

        // 그리디하게 재사용 계산: 정렬 후 매칭
        val originalSorted = prices.sorted().toMutableList()
        val newSorted = newPrices.sorted().toMutableList()

        var reusedCount = 0
        var i = 0
        var j = 0

        while (i < originalSorted.size && j < newSorted.size) {
            if (originalSorted[i] == newSorted[j]) {
                reusedCount++
                i++
                j++
            } else if (originalSorted[i] < newSorted[j]) {
                i++
            } else {
                j++
            }
        }

        // 인쇄해야 할 가격표 개수
        val newTagsToPrint = prices.size - reusedCount

        // 총 수익 = 새로운 가격들의 합 - 인쇄 비용
        return totalNewPrice - (newTagsToPrint * y)
    }
}

fun main() {
    val t = readln().toInt()
    val solution = Solution()

    repeat(t) {
        val (n, y) = readln().split(" ").let { it[0].toInt() to it[1].toLong() }
        val prices = readln().split(" ").map { it.toInt() }.toIntArray()

        println(solution.solve(n, y, prices))
    }
}