package codeforces.`2025-09-16`.b_maximum_cost_permutation

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SolutionTest {
    private val solution = Solution()

    @Test
    fun `test case 1`() {
        val n = 5
        val p = intArrayOf(1, 0, 4, 0, 5)
        val expected = 3
        val result = solution.solve(n, p)
        assertEquals(expected, result, "Expected cost 3 for [1, 0, 4, 0, 5]")
    }

    @Test
    fun `test case 2`() {
        val n = 3
        val p = intArrayOf(0, 0, 0)
        val expected = 3
        val result = solution.solve(n, p)
        assertEquals(expected, result, "Expected cost 3 for [0, 0, 0]")
    }

    @Test
    fun `test case 3`() {
        val n = 4
        val p = intArrayOf(1, 2, 3, 0)
        val expected = 0
        val result = solution.solve(n, p)
        assertEquals(expected, result, "Expected cost 0 for [1, 2, 3, 0] (can be made sorted)")
    }

    @Test
    fun `test case 4`() {
        val n = 3
        val p = intArrayOf(0, 3, 2)
        val expected = 2
        val result = solution.solve(n, p)
        assertEquals(expected, result, "Expected cost 2 for [0, 3, 2]")
    }

    @Test
    fun `edge case - already sorted`() {
        val n = 3
        val p = intArrayOf(1, 2, 3)
        val expected = 0
        val result = solution.solve(n, p)
        assertEquals(expected, result, "Already sorted permutation should have cost 0")
    }

    @Test
    fun `edge case - single element`() {
        val n = 1
        val p = intArrayOf(0)
        val expected = 0
        val result = solution.solve(n, p)
        assertEquals(expected, result, "Single element should have cost 0")
    }
}