package template.graph

/**
 * ✅ 분리 집합(Union-Find / DSU)
 * - 경로 압축 + union by rank로 거의 상수 시간에 집합 병합/질의
 */
class UnionFind(n: Int) {
    private val p = IntArray(n) { it } // 부모
    private val r = IntArray(n)        // 랭크(트리 높이 근사)

    fun find(x: Int): Int {
        // 반복 버전 경로 압축 (재귀 대비 스택 오버플로 우려 적음)
        var a = x
        while (p[a] != a) { p[a] = p[p[a]]; a = p[a] }
        return a
    }

    fun unite(a0: Int, b0: Int): Boolean {
        var a = find(a0); var b = find(b0)
        if (a == b) return false
        // 항상 큰 랭크가 부모가 되도록
        if (r[a] < r[b]) a = b.also { b = a }
        p[b] = a
        if (r[a] == r[b]) r[a]++
        return true
    }
    fun same(a: Int, b: Int) = find(a) == find(b)
}
