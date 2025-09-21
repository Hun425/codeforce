package codeforces.`2024-09-20`.b_multiple_construction

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SolutionTest {

    /**
     * 생성된 배열이 문제의 모든 조건을 만족하는지 검증하는 헬퍼 함수
     */
    private fun isValid(n: Int, solutionStr: String): Boolean {
        val arr = try {
            solutionStr.split(" ").filter { it.isNotBlank() }.map { it.toInt() }.toIntArray()
        } catch (e: NumberFormatException) {
            println("Invalid number format in solution string: $solutionStr")
            return false
        }

        // 조건 1: 배열의 길이는 2*n인가?
        if (arr.size != 2 * n) {
            println("Validation failed: Array length is ${arr.size}, expected ${2 * n}")
            return false
        }

        // 조건 2: 1부터 n까지의 각 숫자가 정확히 두 번 나타나는가?
        val counts = IntArray(n + 1)
        arr.forEach { num ->
            if (num in 1..n) {
                counts[num]++
            } else {
                println("Validation failed: Number $num is out of range [1, $n]")
                return false // 범위 밖의 숫자가 있으면 안 됨
            }
        }
        for (i in 1..n) {
            if (counts[i] != 2) {
                println("Validation failed: Number $i appears ${counts[i]} times, expected 2")
                return false
            }
        }

        // 조건 3: 각 숫자 x의 두 등장 위치 사이의 거리가 x의 배수인가?
        for (x in 1..n) {
            val indices = arr.indices.filter { arr[it] == x }
            // 위에서 개수 체크를 했으므로 indices.size는 항상 2
            val (p1, p2) = indices[0] to indices[1]
            val distance = p2 - p1
            if (distance % x != 0) {
                println("Validation failed: For number $x, distance is $distance, which is not a multiple of $x")
                return false
            }
        }

        return true
    }

    @Test
    fun `예제 테스트 케이스 1 (n=2)`() {
        val n = 2
        val result = solve(n)
        assertTrue(isValid(n, result), "n=$n, result='$result' is not valid")
    }

    @Test
    fun `예제 테스트 케이스 2 (n=3)`() {
        val n = 3
        val result = solve(n)
        assertTrue(isValid(n, result), "n=$n, result='$result' is not valid")
    }

    @Test
    fun `예제 테스트 케이스 3 (n=1)`() {
        val n = 1
        val result = solve(n)
        assertTrue(isValid(n, result), "n=$n, result='$result' is not valid")
    }
}
