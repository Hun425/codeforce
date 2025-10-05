package baekjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

/**
 * 이동을 함수로 정의하고 위치정보를 2차원 배열으로 관리하면 될 거 같다.
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())

    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()

    val color = Array(N) { IntArray(N) }
    val arr = Array(N) { Array(N) { mutableListOf<Int>() } }
    val pieces = Array(K){Piece(0,0,0)}

    repeat(N) {
        st = StringTokenizer(br.readLine())

        for (j in 0 until N) {
            color[it][j] = st.nextToken().toInt()
        }
    }

    repeat(K){
        st = StringTokenizer(br.readLine())

        val r = st.nextToken().toInt()
        val c = st.nextToken().toInt()
        val d = st.nextToken().toInt()

        pieces[it] = Piece(r-1, c-1, d)
        arr[r-1][c-1].add(it)
    }

    // 상 하 좌 우
    val dr = intArrayOf(0, 0, 0, -1, 1)
    val dc = intArrayOf(0, 1, -1, 0, 0)
    fun reverseDir(d: Int) = when (d) {
        1 -> 2
        2 -> 1
        3 -> 4
        4 -> 3
        else -> d
    }
    fun out(nr: Int, nc: Int) = (nr !in 0 until N || nc !in 0 until N)

    fun move(i:Int): Boolean {
        val (r,c,_) = pieces[i]
        val src = arr[r][c]

        val idx = src.indexOf(i)

        if (idx != 0) return false

        val tail = src.subList(idx,src.size)
        val moving = tail.toMutableList()
        tail.clear()

        while(src.size>idx) src.removeAt(idx)

        var d = pieces[i].d
        var nr = r+dr[d]
        var nc = c+dc[d]

        if(out(nr,nc) || color[nr][nc]==2){
            d = reverseDir(d)
            pieces[i].d = d
            nr = r+dr[d]
            nc = c+dc[d]
            if(out(nr,nc) || color[nr][nc]==2){
                arr[r][c].addAll(moving)
                return arr[r][c].size >=4
            }
        }

        if(color[nr][nc]==1) moving.reverse()

        val dst = arr[nr][nc]
        dst.addAll(moving)

        for(p in moving){
            pieces[p].r = nr
            pieces[p].c = nc
        }
        return dst.size >=4
    }


    for (turn in 1..1000){
        for(i in 0 until K){
            if(move(i)){
                println(turn)
                return
            }
        }
    }
    println(-1)
}

data class Piece(var r: Int, var c: Int, var d: Int)
