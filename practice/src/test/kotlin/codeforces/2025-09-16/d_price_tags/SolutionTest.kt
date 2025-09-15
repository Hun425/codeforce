package codeforces.`2025-09-16`.d_price_tags

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class SolutionTest {
    private val solution = Solution()

    @Test
    fun `테스트 케이스 1 - 기본 예제`() {
        // given
        val n = 5
        val y = 51L
        val prices = intArrayOf(50, 150, 50, 148, 150)

        // when
        val result = solution.solve(n, y, prices)

        // then
        assertEquals(31L, result)
    }

    @Test
    fun `테스트 케이스 2 - 높은 인쇄 비용`() {
        // given
        val n = 3
        val y = 1000000000L
        val prices = intArrayOf(42, 42, 42)

        // when
        val result = solution.solve(n, y, prices)

        // then
        assertEquals(-2999999937L, result)
    }

    @Test
    fun `테스트 케이스 3 - 복합 케이스`() {
        // given
        val n = 10
        val y = 54321L
        val prices = intArrayOf(1, 8088, 45, 1, 73, 1, 9198, 4991, 1, 83)

        // when
        val result = solution.solve(n, y, prices)

        // then
        assertEquals(-162755L, result)
    }

    @Test
    fun `테스트 케이스 4 - 모든 가격이 1`() {
        // given
        val n = 3
        val y = 100L
        val prices = intArrayOf(1, 1, 1)

        // when
        val result = solution.solve(n, y, prices)

        // then
        assertEquals(3L, result)
    }

    @Test
    fun `단일 상품 테스트`() {
        // given
        val n = 1
        val y = 10L
        val prices = intArrayOf(100)

        // when
        val result = solution.solve(n, y, prices)

        // then
        // x=2: 새가격=50, 기존가격표 재사용 불가 -> 50-10=40
        // x=3: 새가격=34, 기존가격표 재사용 불가 -> 34-10=24
        // ...
        // x=100: 새가격=1, 기존가격표 재사용 불가 -> 1-10=-9
        assertTrue(result >= -9L)
    }

    @Test
    fun `완전 재사용 가능한 경우`() {
        // given
        val n = 2
        val y = 1000L
        val prices = intArrayOf(4, 2)

        // when
        val result = solution.solve(n, y, prices)

        // then
        // 수동 계산 (정정):
        // x=2: 새가격=[2,1], 기존[4,2] -> 2는 재사용 가능(1개), 1은 불가 -> 재사용 1개, 인쇄 1개
        // 수익: (2+1) - 1*1000 = -997
        // x=4: 새가격=[1,1], 기존[4,2] -> 재사용 0개 -> (1+1) - 2*1000 = -1998
        // 따라서 x=2가 최적이고 결과는 -997
        assertEquals(-997L, result)
    }
}