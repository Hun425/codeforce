package codeforces.`2024-09-20`.a_shortest_increasing_path

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SolutionTest {

    @Test
    fun `예제 테스트 케이스 1`() {
        assertEquals(2, solve(1, 2))
    }

    @Test
    fun `예제 테스트 케이스 2`() {
        assertEquals(2, solve(5, 6))
    }

    @Test
    fun `예제 테스트 케이스 3`() {
        assertEquals(3, solve(4, 2))
    }

    @Test
    fun `예제 테스트 케이스 4`() {
        assertEquals(-1, solve(1, 1))
    }

    @Test
    fun `예제 테스트 케이스 5`() {
        assertEquals(-1, solve(2, 1))
    }

    @Test
    fun `예제 테스트 케이스 6`() {
        assertEquals(-1, solve(3, 3))
    }

    @Test
    fun `예제 테스트 케이스 7`() {
        assertEquals(-1, solve(5, 1))
    }

    @Test
    fun `예제 테스트 케이스 8`() {
        assertEquals(-1, solve(5, 4))
    }

    @Test
    fun `예제 테스트 케이스 9`() {
        assertEquals(2, solve(752, 18572))
    }

    @Test
    fun `예제 테스트 케이스 10`() {
        assertEquals(3, solve(95152, 2322))
    }
}
