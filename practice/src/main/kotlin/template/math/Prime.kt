package template.math

/**
 * ✅ 소수 관련 유틸: 에라토스테네스 체, 정수 소인수분해
 * - sieve(N): 2..N 사이의 소수 목록
 * - factorize(n): n의 소인수분해 결과 (p, cnt) 리스트
 */
object Prime {
    fun sieve(n: Int): IntArray {
        val isPrime = BooleanArray(n + 1) { true }
        isPrime[0] = false; if (n >= 1) isPrime[1] = false
        var p = 2
        while (p * p <= n) {
            if (isPrime[p]) {
                var x = p * p
                while (x <= n) { isPrime[x] = false; x += p }
            }
            p++
        }
        val list = ArrayList<Int>()
        for (i in 2..n) if (isPrime[i]) list.add(i)
        return list.toIntArray()
    }
    fun factorize(n0: Long): MutableList<Pair<Long, Int>> {
        var n = n0
        val res = mutableListOf<Pair<Long, Int>>()
        var p = 2L
        while (p * p <= n) {
            if (n % p == 0L) {
                var cnt = 0
                while (n % p == 0L) { n /= p; cnt++ }
                res.add(p to cnt)
            }
            p++
        }
        if (n > 1) res.add(n to 1)
        return res
    }
}
