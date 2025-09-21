package codeforces.`2025-09-21`.a_equal_occurrences

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

private fun runMainWith(input: String): String {
    val oldIn = System.`in`
    val oldOut = System.out
    val baos = ByteArrayOutputStream()
    return try {
        System.setIn(ByteArrayInputStream(input.toByteArray()))
        System.setOut(PrintStream(baos))
        main() // 호출
        baos.toString().trimEnd() // 마지막 개행 정규화
    } finally {
        System.setIn(oldIn)
        System.setOut(oldOut)
    }
}

class MainTest {

    @Test
    fun `예제 1`() {
        val input = """
            1
            5
            1 1 4 4 4
        """.trimIndent()
        val output = runMainWith(input)
        assertEquals("4", output)
    }

    @Test
    fun `예제 2`() {
        val input = """
            1
            2
            1 2
        """.trimIndent()
        val output = runMainWith(input)
        assertEquals("2", output)
    }

    @Test
    fun `예제 3`() {
        val input = """
            1
            15
            1 1 1 1 1 2 2 2 2 3 3 3 4 4 5
        """.trimIndent()
        val output = runMainWith(input)
        assertEquals("9", output)
    }

    @Test
    fun `예제 4`() {
        val input = """
            1
            5
            3 3 3 3 3
        """.trimIndent()
        val output = runMainWith(input)
        assertEquals("5", output)
    }
}
