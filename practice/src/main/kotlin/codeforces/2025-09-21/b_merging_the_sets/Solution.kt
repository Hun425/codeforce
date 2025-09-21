package codeforces.`2025-09-21`.b_merging_the_sets

import java.util.ArrayDeque

// 현재 테스트케이스 데이터를 전역 저장(요청 템플릿 준수용)
private var nSets = 0
private var mElems = 0
private lateinit var sets: Array<IntArray>

// YES=1, NO=0 을 반환
fun solve(x_int: Int, y_int: Int): Int {
    val n = x_int
    val m = y_int
    // 1) 원소별 포함 집합 목록 만들기
    val contains = Array(m + 1) { mutableListOf<Int>() }
    for (i in 0 until n) {
        for (e in sets[i]) {
            contains[e].add(i)
        }
    }

    // 2) 어떤 원소라도 등장 0이면 즉시 NO
    for (e in 1..m) {
        if (contains[e].isEmpty()) return 0
    }

    // 3) 유일 커버 큐 초기화
    val covered = BooleanArray(m + 1) // 원소가 이미 덮였는지
    val chosen = BooleanArray(n)      // 필수로 선택된 집합 표시
    val aliveSet = BooleanArray(n) { true } // "살아있는 집합" (옵션/미선택 포함)
    val deg = IntArray(m + 1) { contains[it].size } // 미덮인 원소 기준 "살아있는 집합 수"

    val q: ArrayDeque<Int> = ArrayDeque()
    for (e in 1..m) if (deg[e] == 1) q.add(e)

    var chosenCount = 0
    var coveredCount = 0

    fun chooseSet(si: Int) {
        if (!aliveSet[si]) return
        // 이 집합을 "필수 선택"으로 확정
        if (!chosen[si]) {
            chosen[si] = true
            chosenCount++
        }
        // 이 집합이 덮는 원소들을 모두 "덮임" 처리
        for (e in sets[si]) {
            if (!covered[e]) {
                covered[e] = true
                coveredCount++
                // 원소 e는 더 이상 고려대상이 아니므로 deg[e]를 0으로 만들기
                // (큐에 들어갈 일 없도록 음수로 떨어뜨리지 않게 주의)
                for (sj in contains[e]) {
                    if (aliveSet[sj] && sj != si) {
                        // sj는 계속 alive지만, 원소 e는 덮였으므로 deg[e] 감소 필요 없음(이미 커버 완료)
                        // 아무 것도 하지 않음
                    }
                }
                deg[e] = 0
            }
        }
        // 집합 si는 계속 alive(true)로 둔다. (옵션으로 더 추가되더라도 커버는 유지되므로)
        // alive 여부를 굳이 false로 만들 필요는 없다.
    }

    // 4) 유일 커버 반복 처리
    while (q.isNotEmpty()) {
        val e = q.removeFirst()
        if (covered[e]) continue
        // e를 덮는 "살아있는 집합"은 정확히 하나여야 함
        var only = -1
        for (si in contains[e]) {
            if (aliveSet[si]) {
                if (only == -1) only = si else {
                    // deg[e]==1 조건상 오지 않을 케이스
                }
            }
        }
        if (only == -1) continue
        chooseSet(only)

        // 새롭게 덮인 원소들로 인해, 다른 원소들의 deg는 변하지 않지만
        // "아직 덮이지 않은 원소" 중에서 deg==1이 새로 생길 수는 없다
        // (덮인 원소는 deg를 0으로 만든 상태. 유일 커버는 '미덮인 원소'에서만 의미 있음)
        // 따라서 여기서는 추가 큐 삽입이 없음.
        // -> 대신, 유일 커버 초기화 외에는 큐가 더는 확장되지 않음.
    }

    // 5) 코어가 남았는지(= 미덮인 원소가 있는지) 확인
    val uncoveredLeft = (coveredCount < m)
    if (uncoveredLeft) {
        // 코어가 남았다 → 유일 커버가 없고, 각 원소가 적어도 2개의 집합에서 덮임
        // → 분기가 생겨 선택 방법이 3 이상 → YES
        return 1
    }

    // 6) 모두 덮였다면, 남은 집합(= 필수 선택 외의 집합) 수 R = n - chosenCount
    // 이들은 모두 옵션(넣든 말든 커버 유지) → 경우의 수 2^R
    val R = n - chosenCount
    return if (R >= 2) 1 else 0
}

fun main() {
    val t = readln().trim().toInt()
    val out = StringBuilder()
    repeat(t) {
        val (n, m) = readln().trim().split(" ").map { it.toInt() }
        nSets = n
        mElems = m
        sets = Array(n) { IntArray(0) }
        for (i in 0 until n) {
            val parts = readln().trim().split(" ").map { it.toInt() }
            val li = parts[0]
            val arr = IntArray(li) { idx -> parts[idx + 1] }
            sets[i] = arr
        }
        val ans = solve(n, m)
        out.append(if (ans == 1) "YES" else "NO").append('\n')
    }
    print(out.toString())
}
