package baekjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import kotlin.collections.ArrayDeque

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val R = st.nextToken().toInt()
    val C = st.nextToken().toInt()

    val grid = Array(R) { CharArray(C) }
    val waterQ = ArrayDeque<Pair<Int, Int>>()
    val nextWaterQ = ArrayDeque<Pair<Int, Int>>()

    val swans = ArrayList<Pair<Int, Int>>(2)
    for (r in 0 until R) {
        val line = br.readLine()
        for (c in 0 until C) {
            val ch = line[c]
            grid[r][c] = ch
            if (ch != 'X') waterQ.addLast(r to c)            // DIFF: 첫날 모든 물+L을 물 큐에 넣음
            if (ch == 'L') swans.add(r to c)                 // DIFF: L 좌표 2개 수집(매번 스캔 X)
        }
    }

    val L1 = swans[0]
    val L2 = swans[1]

    val dr = intArrayOf(-1, 1, 0, 0)
    val dc = intArrayOf(0, 0, -1, 1)

    // DIFF: 백조 쪽도 “오늘 줄/내일 줄”을 유지(매일 전체 재BFS 금지)
    val visitedSwan = Array(R) { BooleanArray(C) }
    var swanQ = ArrayDeque<Pair<Int, Int>>()
    visitedSwan[L1.first][L1.second] = true                  // DIFF: enqueue 직전 방문표시(시드)
    swanQ.addLast(L1)

    var day = 0                                              // DIFF: day=0에서 시작(오프바이원 수정)

    while (true) {
        // 1) 오늘 백조 확장: 물은 계속, 얼음은 내일 줄로 모음
        val (met, nextSwanQ) = expandSwan(grid, swanQ, visitedSwan, L2, dr, dc)
        if (met) {
            println(day)
            return
        }

        // 2) 하루치 물 녹이기: 경계 한 겹만, 내부에서 X→.로 바꾸고 nextWaterQ 구성
        meltWater(grid, waterQ, nextWaterQ, dr, dc)

        // 3) 내일로 교체
        swanQ = nextSwanQ                                    // DIFF: 백조 줄 스왑(누적 탐색 유지)
        waterQ.addAll(nextWaterQ)                            // DIFF: 물 줄 스왑(내부 클리어 대신 외부 스왑)
        nextWaterQ.clear()
        day++
    }
}

/**
 * 오늘 swanQ(백조가 현재 바로 갈 수 있는 물 경계)를 전부 소진한다.
 * - '.'/'L'은 오늘 바로 계속 탐색(swanQ에 붙임)
 * - 'X'는 오늘은 못 가므로 내일 시작점(nextSwanQ)에 모은다
 * - 목표 L2에 닿으면 즉시 true
 *
 * DIFF 요지:
 *  - checkMeet처럼 매일 전체를 처음부터 BFS하지 않음(누적 visited 유지)
 *  - enqueue 직전에 visited 표시 → 중복 삽입 차단
 */
private fun expandSwan(
    grid: Array<CharArray>,
    swanQ: ArrayDeque<Pair<Int, Int>>,
    visited: Array<BooleanArray>,
    target: Pair<Int, Int>,
    dr: IntArray,
    dc: IntArray
): Pair<Boolean, ArrayDeque<Pair<Int, Int>>> {
    val next = ArrayDeque<Pair<Int, Int>>()
    val R = grid.size
    val C = grid[0].size

    while (swanQ.isNotEmpty()) {
        val (r, c) = swanQ.removeFirst()
        for (k in 0 until 4) {
            val nr = r + dr[k]
            val nc = c + dc[k]
            if (nr !in 0 until R || nc !in 0 until C) continue
            if (visited[nr][nc]) continue

            val ch = grid[nr][nc]
            if (ch == 'X') {
                visited[nr][nc] = true           // DIFF: 내일 시작점도 방문표시해서 중복 방지
                next.addLast(nr to nc)
            } else {
                if (nr == target.first && nc == target.second) return true to next
                visited[nr][nc] = true
                swanQ.addLast(nr to nc)
            }
        }
    }
    return false to next
}

/**
 * 오늘 물 경계(waterQ)를 전부 소진하여, 맞닿은 얼음만 녹여 '.'로 바꾸고
 * 그 칸들을 내일 물 경계(nextWaterQ)에 모은다.
 *
 * DIFF 요지:
 *  - 하루치(한 겹)만 처리: while(waterQ.isNotEmpty())
 *  - X를 발견하면 즉시 '.'로 바꿔 중복 방지(visited 배열 불필요)
 *  - 스왑은 바깥에서 수행(명확한 “하루” 경계)
 */
private fun meltWater(
    grid: Array<CharArray>,
    waterQ: ArrayDeque<Pair<Int, Int>>,
    nextWaterQ: ArrayDeque<Pair<Int, Int>>,
    dr: IntArray,
    dc: IntArray
) {
    val R = grid.size
    val C = grid[0].size

    while (waterQ.isNotEmpty()) {
        val (r, c) = waterQ.removeFirst()
        for (k in 0 until 4) {
            val nr = r + dr[k]
            val nc = c + dc[k]
            if (nr !in 0 until R || nc !in 0 until C) continue
            if (grid[nr][nc] == 'X') {
                grid[nr][nc] = '.'               // DIFF: 즉시 녹여서 중복 enqueue 방지
                nextWaterQ.addLast(nr to nc)     // 내일 물 경계
            }
        }
    }
}
