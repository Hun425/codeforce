package template.common

/**
 * ✅ 방향 벡터 (상/하/좌/우), (8방향)
 * - 격자 BFS/DFS에서 상수로 쓰기 편하도록 모아둔 유틸
 */
object Dir4 {
    val dr = intArrayOf(-1, 1, 0, 0)
    val dc = intArrayOf(0, 0, -1, 1)
}
object Dir8 {
    val dr = intArrayOf(-1,-1,-1, 0, 0, 1, 1, 1)
    val dc = intArrayOf(-1, 0, 1,-1, 1,-1, 0, 1)
}

/** 범위를 벗어난 값 보정 */
inline fun clamp(x: Int, lo: Int, hi: Int) = if (x < lo) lo else if (x > hi) hi else x

/** 리스트 원소 교환 */
inline fun <T> swap(a: MutableList<T>, i: Int, j: Int) { val t = a[i]; a[i] = a[j]; a[j] = t }

/**
 * ✅ 이진 탐색 계열 유틸: lowerBound/upperBound
 * - 정렬된 리스트에서 key의 삽입 위치를 찾을 때 사용
 */
fun <T: Comparable<T>> lowerBound(a: List<T>, key: T): Int {
    var l = 0; var r = a.size
    while (l < r) {
        val m = (l + r) ushr 1
        if (a[m] < key) l = m + 1 else r = m
    }
    return l
}
fun <T: Comparable<T>> upperBound(a: List<T>, key: T): Int {
    var l = 0; var r = a.size
    while (l < r) {
        val m = (l + r) ushr 1
        if (a[m] <= key) l = m + 1 else r = m
    }
    return l
}
