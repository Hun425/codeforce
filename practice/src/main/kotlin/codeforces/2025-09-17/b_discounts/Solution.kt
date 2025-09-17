package codeforces.`2025-09-17`.b_discounts

class Solution {
    fun solve(n: Int, k: Int, a: LongArray, b: IntArray): Long {
        a.sortDescending()
        b.sort()

        var totalCost = a.sum()
        var productPtr = 0

        // 바우처를 작은 것부터 사용 (최적 전략)
        for (voucherSize in b) {
            if (productPtr + voucherSize <= n) {
                if (voucherSize == 1) {
                    // b=1: 해당 상품 무료
                    totalCost -= a[productPtr]
                } else {
                    // b>1: 그룹에서 가장 저렴한 상품 무료
                    totalCost -= a[productPtr + voucherSize - 1]
                }
                productPtr += voucherSize
            }
        }

        return totalCost
    }
}

fun main() {
    val t = readln().toInt()
    val solution = Solution()
    repeat(t) {
        val (n, k) = readln().split(" ").map { it.toInt() }
        val a = readln().split(" ").map { it.toLong() }.toLongArray()
        val b = readln().split(" ").map { it.toInt() }.toIntArray()
        println(solution.solve(n, k, a, b))
    }
}
