package codeforces.`2025-09-17`.d1_inversion_graph_coloring_easy_version

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SolutionTest {

    private val solution = Solution()

    @Test
    fun `예제 1`() {
        val n = 4
        val a = listOf(4, 2, 3, 1)
        val expected = 13L
        assertEquals(expected, solution.solve(n, a))
    }

    @Test
    fun `예제 2`() {
        val n = 7
        val a = listOf(7, 6, 1, 2, 3, 3, 2)
        val expected = 73L
        assertEquals(expected, solution.solve(n, a))
    }

    @Test
    fun `예제 3`() {
        val n = 5
        val a = listOf(1, 1, 1, 1, 1)
        val expected = 32L
        assertEquals(expected, solution.solve(n, a))
    }

    @Test
    fun `예제 4`() {
        val n = 11
        val a = listOf(7, 2, 1, 9, 7, 3, 4, 1, 3, 5, 3)
        val expected = 619L
        assertEquals(expected, solution.solve(n, a))
    }
}
