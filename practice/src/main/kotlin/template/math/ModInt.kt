package template.math

/**
 * ✅ 모듈러 정수 + 조합 전처리
 * - 페르마의 소정리를 이용한 역원: a^(p-2) mod p (p가 소수일 때)
 */
class ModInt(val mod: Long) {
    fun add(a: Long, b: Long) = (a + b) % mod
    fun sub(a: Long, b: Long) = (a - b + mod) % mod
    fun mul(a: Long, b: Long) = ((a % mod) * (b % mod)) % mod
    fun pow(a0: Long, e0: Long): Long {
        var a = a0 % mod; var e = e0; var r = 1L
        while (e > 0) { if ((e and 1L) == 1L) r = mul(r, a); a = mul(a, a); e = e shr 1 }
        return r
    }
    fun inv(a: Long) = pow(a, mod - 2)
}

class Comb(n: Int, val mod: Long) {
    private val fact = LongArray(n + 1)
    private val invFact = LongArray(n + 1)
    private val mi = ModInt(mod)
    init {
        fact[0] = 1L
        for (i in 1..n) fact[i] = mi.mul(fact[i-1], i.toLong())
        invFact[n] = mi.inv(fact[n])
        for (i in n - 1 downTo 0) invFact[i] = mi.mul(invFact[i+1], (i+1).toLong())
    }
    fun nCk(n: Int, k: Int): Long {
        if (k < 0 || k > n) return 0
        return (((fact[n] * invFact[k]) % mod) * invFact[n-k]) % mod
    }
}
