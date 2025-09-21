package codeforces.`2025-09-21`.b_merging_the_sets

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
        main()
        baos.toString().trimEnd()
    } finally {
        System.setIn(oldIn)
        System.setOut(oldOut)
    }
}

class MainTest {
    @Test
    fun `문제 예제`() {
        val input = """
            6
            3 2
            2 1 2
            1 1
            1 2
            4 10
            3 1 2 3
            2 4 5
            1 6
            4 7 8 9 10
            2 5
            4 1 2 3 4
            4 1 2 3 4
            5 5
            5 1 2 3 4 5
            5 1 2 3 4 5
            5 1 2 3 4 5
            5 1 2 3 4 5
            5 1 2 3 4 5
            5 10
            4 1 2 3 4
            5 1 2 5 6 7
            5 2 6 7 8 9
            4 6 7 8 9
            2 9 10
            5 5
            1 1
            1 2
            1 3
            2 4 5
            1 5
        """.trimIndent()

        val expected = """
            YES
            NO
            NO
            YES
            YES
            NO
        """.trimIndent()

        val output = runMainWith(input)
        assertEquals(expected, output)
    }
}
