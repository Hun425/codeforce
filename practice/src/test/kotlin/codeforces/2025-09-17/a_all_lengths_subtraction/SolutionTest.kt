package codeforces.`2025-09-17`.a_all_lengths_subtraction

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SolutionTest {

    private val solution = Solution()

    @Test
    fun `테스트 케이스 1`() {
        // given
        val n = 4
        val p = intArrayOf(1, 3, 4, 2)

        // when
        val result = solution.solve(n, p)

        // then
        assertEquals("YES", result)
    }

    @Test
    fun `테스트 케이스 2`() {
        // given
        val n = 5
        val p = intArrayOf(1, 5, 2, 4, 3)

        // when
        val result = solution.solve(n, p)

        // then
        assertEquals("NO", result)
    }

    @Test
    fun `테스트 케이스 3`() {
        // given
        val n = 5
        val p = intArrayOf(2, 4, 5, 3, 1)

        // when
        val result = solution.solve(n, p)

        // then
        assertEquals("YES", result)
    }

    @Test
    fun `테스트 케이스 4`() {
        // given
        val n = 3
        val p = intArrayOf(3, 1, 2)

        // when
        val result = solution.solve(n, p)

        // then
        assertEquals("NO", result)
    }
}
