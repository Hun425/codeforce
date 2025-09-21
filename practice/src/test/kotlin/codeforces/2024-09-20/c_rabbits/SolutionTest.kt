package codeforces.`2024-09-20`.c_rabbits

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SolutionTest {

    @Test
    fun `예제 1`() {
        assertEquals("YES", solve(4, "0100"))
    }

    @Test
    fun `예제 2`() {
        assertEquals("YES", solve(3, "000"))
    }

    @Test
    fun `예제 3`() {
        assertEquals("NO", solve(8, "11011011"))
    }

    @Test
    fun `예제 4`() {
        assertEquals("YES", solve(5, "00100"))
    }

    @Test
    fun `예제 5`() {
        assertEquals("YES", solve(1, "1"))
    }

    @Test
    fun `예제 6`() {
        assertEquals("YES", solve(5, "01011"))
    }

    @Test
    fun `예제 7`() {
        assertEquals("YES", solve(2, "01"))
    }

    @Test
    fun `예제 8`() {
        assertEquals("YES", solve(7, "0101011"))
    }

    @Test
    fun `예제 9`() {
        assertEquals("YES", solve(7, "1101010"))
    }

    @Test
    fun `예제 10`() {
        assertEquals("YES", solve(5, "11001"))
    }

    @Test
    fun `예제 11`() {
        assertEquals("NO", solve(4, "1101"))
    }

    @Test
    fun `예제 12`() {
        assertEquals("NO", solve(9, "001101100"))
    }
}
