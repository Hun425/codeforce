package codeforces.`2025-09-16`.d_price_tags

import kotlin.math.max
import kotlin.math.min

class Solution {
    fun solve(n: Int, y: Long, prices: IntArray): Long {
        val maxPrice = prices.maxOrNull() ?: 0
        if (maxPrice == 0) {
            return 0
        }

        val counts = LongArray(maxPrice + 1)
        for (price in prices) {
            counts[price]++
        }

        val prefixCounts = LongArray(maxPrice + 2)
        for (i in 0..maxPrice) {
            prefixCounts[i + 1] = prefixCounts[i] + counts[i]
        }

        var maxProfit = Long.MIN_VALUE

        // x는 1보다 큰 정수. x가 maxPrice + 1보다 커지면 모든 새 가격은 1이 되므로 그 이상은 볼 필요가 없다.
        for (x in 2..maxPrice + 1) {
            var currentTotalValue = 0L
            var tagsToPrint = 0L

            // p는 새 가격(new price)
            var p = 1
            while (true) {
                // 새 가격이 p가 되는 기존 가격의 범위: ( (p-1)*x, p*x ]
                val startRange = (p - 1L) * x + 1
                if (startRange > maxPrice) {
                    break
                }
                val endRange = p.toLong() * x

                // 누적 합 배열을 사용하여 해당 범위의 상품 개수를 O(1)에 계산
                val numItems = prefixCounts[min(endRange, maxPrice.toLong()).toInt() + 1] - prefixCounts[startRange.toInt()]

                if (numItems > 0) {
                    // 새 가격의 총합
                    currentTotalValue += p.toLong() * numItems
                    // 재사용할 수 없는, 새로 인쇄해야 하는 가격표의 수
                    val originalCountP = if (p <= maxPrice) counts[p] else 0
                    tagsToPrint += max(0, numItems - originalCountP)
                }
                p++
            }

            val currentProfit = currentTotalValue - tagsToPrint * y
            if (currentProfit > maxProfit) {
                maxProfit = currentProfit
            }
        }

        return maxProfit
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
