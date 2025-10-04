package baekjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())

    val N = st.nextToken().toInt()
    val Q = st.nextToken().toInt()

    val arr = Array(N+1){mutableListOf<Pair<Int,Int>>()}

    repeat(N-1){
        val (a,b,w) = br.readLine().split(" ").map{ it.toInt()}
        arr[a].add(b to w)
        arr[b].add(a to w)
    }

    repeat(Q){
        val (k,v) = br.readLine().split(" ").map{ it.toInt()}

        println(bfs(k.toLong(),v,arr))
    }
}

fun bfs(k: Long, start: Int, arr: Array<MutableList<Pair<Int,Int>>>):Long {
    var count = 0L
    val visited = BooleanArray(arr.size)
    val q = ArrayDeque<Int>()

    q.add(start)
    visited[start] = true

    while(q.isNotEmpty()) {
        val u = q.removeFirst()

        for((v,w) in arr[u]){
            if(!visited[v] && w >= k){
                q.add(v)
                visited[v] = true
                count++
            }
        }
    }


    return count
}

