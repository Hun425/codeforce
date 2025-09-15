package leetcode.p1935

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SolutionTest {

    private val solution = Solution()

    @Test
    fun testExample1() {
        val text = "hello world"
        val brokenLetters = "ad"
        val expected = 1
        val actual = solution.canBeTypedWords(text, brokenLetters)
        assertEquals(expected, actual)
    }

    @Test
    fun testExample2() {
        val text = "leet code"
        val brokenLetters = "lt"
        val expected = 1
        val actual = solution.canBeTypedWords(text, brokenLetters)
        assertEquals(expected, actual)
    }

    @Test
    fun testExample3() {
        val text = "leet code"
        val brokenLetters = "e"
        val expected = 0
        val actual = solution.canBeTypedWords(text, brokenLetters)
        assertEquals(expected, actual)
    }
}
