package codeforces.`2025-09-17`.c_max_tree

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class SolutionTest {

    /**
     * 표준 입력을 모의하고 표준 출력을 캡처하여 `solve()` 함수를 테스트하는 헬퍼 함수입니다.
     * @param input 테스트에 사용할 입력 문자열입니다.
     * @param expected 예상되는 출력 문자열입니다.
     */
    private fun testSolution(input: String, expected: String) {
        // 표준 입력을 ByteArrayInputStream으로 대체하여 입력 문자열을 제공합니다.
        val inputStream = ByteArrayInputStream(input.toByteArray())
        // 표준 출력을 ByteArrayOutputStream으로 대체하여 결과를 캡처합니다.
        val outputStream = ByteArrayOutputStream()
        val printStream = PrintStream(outputStream)

        // 원래의 System.in과 System.out을 백업합니다.
        val originalIn = System.`in`
        val originalOut = System.out

        try {
            // System.in과 System.out을 우리가 만든 스트림으로 설정합니다.
            System.setIn(inputStream)
            System.setOut(printStream)

            // 테스트할 함수를 호출합니다.
            solve()

            // 캡처된 출력을 예상 결과와 비교합니다.
            val output = outputStream.toString().trim()
            assertEquals(expected, output)
        } finally {
            // 테스트가 끝나면 원래의 System.in과 System.out으로 복원합니다.
            System.setIn(originalIn)
            System.setOut(originalOut)
        }
    }

    @Test
    fun `예제 테스트 케이스 1`() {
        val input = """
            3
            1 2 2 1
            2 3 3 2
        """.trimIndent()
        val expected = "3 2 1"
        testSolution(input, expected)
    }

    @Test
    fun `예제 테스트 케이스 2`() {
        val input = """
            5
            1 2 1 3
            1 5 2 1
            2 4 5 7
            2 3 1 100
        """.trimIndent()
        // 위상 정렬의 결과는 여러 가지가 될 수 있지만, 현재 구현은 결정론적이므로
        // 특정 출력을 예상할 수 있습니다.
        val expected = "2 3 5 4 1"
        testSolution(input, expected)
    }

    @Test
    fun `예제 테스트 케이스 3`() {
        val input = """
            5
            2 5 5 2
            3 5 4 6
            4 5 1 5
            1 5 3 5
        """.trimIndent()
        val expected = "1 5 2 3 4"
        testSolution(input, expected)
    }
}
