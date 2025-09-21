package codeforces.`2024-09-20`.a_shortest_increasing_path

fun main() {
    val t = readln().toInt()
    repeat(t) {
        val (x, y) = readln().split(" ").map { it.toInt() }
        println(solve(x, y))
    }
}

fun solve(x_int: Int, y_int: Int): Int {
    val x = x_int.toLong()
    val y = y_int.toLong()

    // 정리된 필요·충분 조건:
    // y > x -> 2
    // x > y && y >= 2 && x - y >= 2 -> 3
    // 그 외 -> -1 (x == y 또는 부족 조건)
    return when {
        y > x -> 2
        x > y -> {
            if (y >= 2L && x - y >= 2L) 3 else -1
        }
        else -> -1
    }
}
