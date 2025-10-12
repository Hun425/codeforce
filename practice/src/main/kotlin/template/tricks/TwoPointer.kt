package template.tricks

/**
 * ✅ 투 포인터 예시: 정렬된 배열에서 합 <= K인 쌍 개수 세기
 * - i는 왼쪽에서 증가, j는 오른쪽에서 감소하며 영역을 줄임
 */
fun countPairsLE(a: IntArray, K: Int): Long {
    var i = 0; var j = a.size - 1; var cnt = 0L
    while (i < j) {
        if (a[i] + a[j] <= K) { cnt += (j - i); i++ } else j--
    }
    return cnt
}
