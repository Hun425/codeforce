package codeforces.`2025-09-17`.a_all_lengths_subtraction

class Solution {
    fun solve(n: Int, p: IntArray): String {
        // p_ext[i]는 원본 순열의 i-1 인덱스 값에 해당하며, p_ext[0] = 0으로 둡니다.
        val pExt = LongArray(n + 1).apply {
            p.forEachIndexed { index, value -> this[index + 1] = value.toLong() }
        }

        // availableLengths[k]가 true이면 길이 k를 사용할 수 있음을 의미합니다.
        val availableLengths = BooleanArray(n + 1) { it > 0 }
        // endsCountAt[i]는 인덱스 i에서 끝나는 연산의 수를 저장합니다.
        val endsCountAt = IntArray(n)

        // i = 0부터 n-1까지 순회하며 각 위치에서 시작해야 할 연산을 결정합니다.
        for (i in 0 until n) {
            val prevP = pExt[i]
            val currentP = pExt[i + 1]
            // 인덱스 i-1에서 끝난 연산의 수
            val endedBefore = if (i > 0) endsCountAt[i - 1] else 0

            // C_i = C_{i-1} + S_i - E_{i-1}  (C: 총 연산 수, S: 시작 수, E: 종료 수)
            // S_i = C_i - C_{i-1} + E_{i-1}
            val mustStartCount = currentP - prevP + endedBefore

            if (mustStartCount < 0) {
                return "NO"
            }

            var needed = mustStartCount
            if (needed > 0) {
                // 현재 위치 i에서 시작할 수 있는 가장 긴 길이부터 탐욕적으로 할당합니다.
                // 시작 위치가 i일 때 가능한 최대 길이는 n-i 입니다.
                for (k in (n - i) downTo 1) {
                    if (needed == 0L) break
                    if (availableLengths[k]) {
                        // 길이 k를 사용합니다.
                        availableLengths[k] = false
                        val endIndex = i + k - 1
                        endsCountAt[endIndex]++
                        needed--
                    }
                }
            }

            // 필요한 만큼의 길이를 할당하지 못한 경우
            if (needed > 0) {
                return "NO"
            }
        }

        return "YES"
    }
}

fun main() {
    val t = readln().toInt()
    val solution = Solution()
    repeat(t) {
        val n = readln().toInt()
        val p = readln().split(" ").map { it.toInt() }.toIntArray()
        println(solution.solve(n, p))
    }
}
