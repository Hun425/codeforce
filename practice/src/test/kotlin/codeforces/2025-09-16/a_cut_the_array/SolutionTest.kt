package codeforces.`2025-09-16`.a_cut_the_array

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SolutionTest {
    private val solution = Solution()

    /**
     * Checks if a given split (l, r) is valid for the array a.
     */
    private fun check(n: Int, a: IntArray, l: Int, r: Int): Boolean {
        if (l <= 0 || r <= l || r >= n) return false

        val prefixSum = LongArray(n + 1)
        for (i in 0 until n) {
            prefixSum[i + 1] = prefixSum[i] + a[i]
        }

        val s1 = prefixSum[l] % 3
        val s2 = (prefixSum[r] - prefixSum[l]) % 3
        val s3 = (prefixSum[n] - prefixSum[r]) % 3

        val allSame = s1 == s2 && s2 == s3
        val allDifferent = s1 != s2 && s2 != s3 && s1 != s3

        return allSame || allDifferent
    }

    @Test
    fun `test case 1`() {
        val n = 6
        val a = intArrayOf(1, 2, 3, 4, 5, 6)
        val (l, r) = solution.solve(n, a)
        // The example output is (3, 5), but other solutions like (1, 2) exist.
        // We verify that the returned solution is valid.
        assertTrue(check(n, a, l, r), "The solution ($l, $r) is not valid.")
    }

    @Test
    fun `test case 2`() {
        val n = 4
        val a = intArrayOf(1, 3, 3, 7)
        val expected = Pair(0, 0)
        val result = solution.solve(n, a)
        assertEquals(expected, result, "Expected (0, 0) as no solution exists.")
    }

    @Test
    fun `test case 3`() {
        val n = 3
        val a = intArrayOf(2, 1, 0)
        val (l, r) = solution.solve(n, a)
        assertTrue(check(n, a, l, r), "The solution ($l, $r) is not valid.")
    }

    @Test
    fun `test case 4`() {
        val n = 5
        val a = intArrayOf(7, 2, 6, 2, 4)
        val (l, r) = solution.solve(n, a)
        assertTrue(check(n, a, l, r), "The solution ($l, $r) is not valid.")
    }
}
