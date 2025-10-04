package codeforces.`2025-09-20`.c_rabbits

fun solve(n: Int, s: String): String {
    fun safe(i: Int): Boolean {
        // 1) 가장자리 회피
        if (i == 0 || i == n - 1) return true

        // 2) 옆에 토끼(=0) 있는 쪽으로 향하기
        if (s[i - 1] == '0' || s[i + 1] == '0') return true

        // 3) 0 1 0 충돌 패턴
        if (i >= 2 && s[i - 1] == '1' && s[i - 2] == '0') return true
        if (i + 2 < n && s[i + 1] == '1' && s[i + 2] == '0') return true

        return false
    }

    for (i in 0 until n) {
        if (s[i] == '0' && !safe(i)) return "NO"
    }
    return "YES"
}
