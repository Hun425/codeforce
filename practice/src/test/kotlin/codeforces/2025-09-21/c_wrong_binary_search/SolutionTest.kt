package codeforces.`2025-09-24`.c_wrong_binary_search

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
        main() // 제출용 main 호출
        baos.toString().trimEnd() // 마지막 개행만 정규화
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
            3
            111
            5
            00000
            5
            10100
            7
            0010000
            11
            00001001100
            12
            011100010000
        """.trimIndent()

        // ⚠️ 주의: 이 문제는 YES일 때 순열이 여러 개 가능.
        // 아래 expected는 "내 정답 코드"가 생성하는 '결정적(deterministic)' 순열에 맞춰 작성했어.
        // (연속 '0' 구간은 원형 시프트로 [L+1, …, R, L] 배치, '1'은 고정점)
        val expected = """
            YES
            1 2 3
            YES
            2 3 4 5 1
            NO
            YES
            2 1 3 5 6 7 4
            YES
            2 3 4 1 5 7 6 8 9 11 10
            NO
        """.trimIndent()

        val output = runMainWith(input)
        assertEquals(expected, output)
    }
}
