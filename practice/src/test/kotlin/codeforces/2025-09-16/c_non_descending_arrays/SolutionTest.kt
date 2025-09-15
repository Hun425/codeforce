package codeforces.`2025-09-16`.c_non_descending_arrays

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SolutionTest {
    private val solution = Solution()

    @Test
    fun `테스트 케이스 1`() {
        val n = 3
        val a = intArrayOf(2, 1, 4)
        val b = intArrayOf(1, 3, 2)
        val expected = 2
        val result = solution.solve(n, a, b)
        assertEquals(expected, result, "Expected 2 good subsets for [2,1,4] and [1,3,2]")
    }

    @Test
    fun `테스트 케이스 2`() {
        val n = 1
        val a = intArrayOf(4)
        val b = intArrayOf(4)
        val expected = 2
        val result = solution.solve(n, a, b)
        assertEquals(expected, result, "Expected 2 good subsets for [4] and [4]")
    }

    @Test
    fun `테스트 케이스 3`() {
        val n = 5
        val a = intArrayOf(2, 3, 3, 4, 4)
        val b = intArrayOf(1, 1, 3, 5, 6)
        val expected = 8
        val result = solution.solve(n, a, b)
        assertEquals(expected, result, "Expected 8 good subsets for [2,3,3,4,4] and [1,1,3,5,6]")
    }

    @Test
    fun `이미 정렬된 배열 테스트`() {
        val n = 3
        val a = intArrayOf(1, 2, 3)
        val b = intArrayOf(4, 5, 6)
        val result = solution.solve(n, a, b)
        // 적어도 공집합은 항상 좋은 부분집합이어야 함
        assert(result >= 1) { "At least empty subset should be valid" }
    }

    @Test
    fun `교환이 필요한 경우 테스트`() {
        val n = 2
        val a = intArrayOf(2, 1)
        val b = intArrayOf(1, 3)
        val result = solution.solve(n, a, b)
        assert(result >= 1) { "At least one valid subset should exist" }
    }

    @Test
    fun `큰 입력 시간 복잡도 테스트`() {
        val n = 100
        val a = IntArray(n) { i -> i + 1 }  // [1, 2, 3, ..., 100]
        val b = IntArray(n) { i -> i + 1 }  // [1, 2, 3, ..., 100]

        val startTime = System.currentTimeMillis()
        val result = solution.solve(n, a, b)
        val endTime = System.currentTimeMillis()

        val executionTime = endTime - startTime
        println("Execution time for n=100: ${executionTime}ms")

        // 이미 정렬된 경우이므로 모든 2^100 경우가 유효하지만,
        // DP 상태는 (100, 100) 하나뿐이므로 빠르게 실행되어야 함
        assert(executionTime < 1000) { "Should execute within 1 second for n=100" }
        assert(result > 0) { "Should have valid result" }
    }
}