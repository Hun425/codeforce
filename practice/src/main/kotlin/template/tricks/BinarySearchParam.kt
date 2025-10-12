package template.tricks

/**
 * ✅ 매개변수 탐색(Parametric Search)
 * - 단조성(답이 성립하면 그 이상/이하도 성립)이 있을 때
 * - "가능?" 판별 함수 feasible(x)를 이용해 답을 O(log R) 내에 탐색
 */
fun binarySearchInt(lo: Int, hi: Int, feasible: (Int) -> Boolean): Int {
    var l = lo; var r = hi; var ans = hi
    while (l <= r) {
        val m = (l + r) ushr 1
        if (feasible(m)) { ans = m; r = m - 1 } else l = m + 1
    }
    return ans
}
fun binarySearchLong(lo: Long, hi: Long, feasible: (Long) -> Boolean): Long {
    var l = lo; var r = hi; var ans = hi
    while (l <= r) {
        val m = (l + r) ushr 1
        if (feasible(m)) { ans = m; r = m - 1 } else l = m + 1
    }
    return ans
}
