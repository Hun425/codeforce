package codeforces.`2025-09-17`.b_discounts

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SolutionTest {

    private val solution = Solution()

    @Test
    fun `테스트 케이스 1`() {
        // given
        val n = 5
        val k = 3
        val a = longArrayOf(18, 3, 7, 2, 9)
        val b = intArrayOf(3, 1, 1)

        // when
        val result = solution.solve(n, k, a, b)

        // then
        assertEquals(10, result)
    }

    @Test
    fun `테스트 케이스 2`() {
        // given
        val n = 6
        val k = 1
        val a = longArrayOf(1, 2, 6, 3, 3, 4)
        val b = intArrayOf(5)

        // when
        val result = solution.solve(n, k, a, b)

        // then
        assertEquals(17, result)
    }

    @Test
    fun `테스트 케이스 3`() {
        // given
        val n = 2
        val k = 3
        val a = longArrayOf(1, 1)
        val b = intArrayOf(2, 2, 2)

        // when
        val result = solution.solve(n, k, a, b)

        // then
        assertEquals(1, result)
    }

    @Test
    fun `테스트 케이스 4`() {
        // given
        val n = 1
        val k = 1
        val a = longArrayOf(10)
        val b = intArrayOf(1)

        // when
        val result = solution.solve(n, k, a, b)

        // then
        assertEquals(0, result)
    }

    @Test
    fun `테스트 케이스 5`() {
        // given
        val n = 5
        val k = 3
        val a = longArrayOf(99, 99, 999, 999, 123)
        val b = intArrayOf(2, 1, 4)

        // when
        val result = solution.solve(n, k, a, b)

        // then
        assertEquals(1197, result)
    }
}
