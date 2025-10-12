package template.string

/**
 * ✅ 다항식 롤링 해시
 * - 충돌 회피를 위해 보통 '이중 해시'를 권장 (여기선 단일 예시)
 * - get(l, r): [l, r) 서브스트링의 해시값
 */
class RollingHash(s: String, private val base: Long = 911382323, private val mod: Long = 972663749) {
    private val n = s.length
    private val p = LongArray(n + 1)
    private val h = LongArray(n + 1)
    init {
        p[0] = 1L
        for (i in 1..n) p[i] = (p[i-1] * base) % mod
        for (i in 0 until n) h[i+1] = (h[i] * base + s[i].code) % mod
    }
    fun get(l: Int, r: Int): Long { // [l, r)
        var res = (h[r] - (h[l] * p[r - l]) % mod)
        if (res < 0) res += mod
        return res
    }
}
