package template.tricks

/**
 * ✅ 좌표 압축
 * - 원소의 상대적 순서만 중요할 때 값들을 0..K-1 범위로 압축
 */
fun compress(a: IntArray): Pair<IntArray, IntArray> {
    val vals = a.clone()
    vals.sort()
    val uniq = java.util.ArrayList<Int>()
    var prev: Int? = null
    for (v in vals) {
        if (prev == null || prev != v) uniq.add(v)
        prev = v
    }
    val map = HashMap<Int, Int>()
    for ((i, v) in uniq.withIndex()) map[v] = i
    val b = IntArray(a.size) { map[a[it]]!! } // 압축된 인덱스 배열
    return b to uniq.toIntArray()            // (압축 결과, 원래 값 목록)
}
