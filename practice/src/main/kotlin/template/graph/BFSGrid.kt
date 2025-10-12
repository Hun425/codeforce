package template.graph

import template.common.Dir4
import java.util.ArrayDeque

/**
 * ✅ 격자 BFS 템플릿
 * - dist[r][c]에 시작점으로부터의 최단 거리(간선 가중치가 모두 1일 때)를 저장합니다.
 * - passable(r, c) 람다로 이동 가능 여부를 외부에서 정의합니다.
 *
 * ⛏️ 흐름
 * 1) 시작점 거리 0으로 세팅하고 큐에 삽입
 * 2) 큐에서 pop → 4방향으로 확장 (경계/장애물/방문 여부 체크)
 * 3) 처음 방문한 칸의 dist = 현재 dist + 1, 큐에 push
 * 4) 큐가 빌 때까지 반복
 *
 * ⏱️ 복잡도: O(R*C)
 */
data class Cell(val r: Int, val c: Int)

fun bfsGrid(start: Cell, R: Int, C: Int, passable: (Int, Int) -> Boolean): Array<IntArray> {
    val dist = Array(R) { IntArray(C) { -1 } } // -1은 미방문
    val q: ArrayDeque<Cell> = ArrayDeque()
    dist[start.r][start.c] = 0
    q.add(start)
    while (q.isNotEmpty()) {
        val (r, c) = q.removeFirst()
        for (k in 0 until 4) {
            val nr = r + Dir4.dr[k]
            val nc = c + Dir4.dc[k]
            if (nr !in 0 until R || nc !in 0 until C) continue // 경계 체크
            if (!passable(nr, nc)) continue                    // 벽/장애물
            if (dist[nr][nc] != -1) continue                   // 이미 방문
            dist[nr][nc] = dist[r][c] + 1
            q.add(Cell(nr, nc))
        }
    }
    return dist
}
