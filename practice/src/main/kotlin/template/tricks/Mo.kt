package template.tricks

/**
 * ✅ Mo's 알고리즘 (오프라인 구간 질의)
 * - 쿼리들을 sqrt 분할 정렬 → 포인터 이동으로 상태를 유지하며 답을 갱신
 * - add/remove 부분만 과제에 맞게 채워서 사용
 */
class Mo(private val n: Int) {
    data class Q(val l: Int, val r: Int, val idx: Int)
    fun solve(queries: List<Q>): LongArray {
        val BS = maxOf(1, Math.sqrt(n.toDouble()).toInt())
        val ord = queries.sortedWith(compareBy<Q>({ it.l / BS }, { if (((it.l / BS) and 1) == 0) it.r else -it.r }))
        var curL = 0; var curR = -1
        val ans = LongArray(queries.size)

        // 문제별 상태(예: 빈도/쌍 개수 등)를 여기에 둡니다.
        fun add(x: Int) { /* TODO: 오른쪽으로 확장/왼쪽으로 확장 시 들어오는 원소 추가 */ }
        fun remove(x: Int) { /* TODO: 범위를 줄일 때 나가는 원소 제거 */ }

        for (q in ord) {
            while (curL > q.l) { curL--; add(curL) }
            while (curR < q.r) { curR++; add(curR) }
            while (curL < q.l) { remove(curL); curL++ }
            while (curR > q.r) { remove(curR); curR-- }
            ans[q.idx] = 0L // TODO: 현재 상태에서 답 읽기
        }
        return ans
    }
}
