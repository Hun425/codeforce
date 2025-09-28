package baekjoon

import java.util.StringTokenizer

data class Edge(val to: Int, val weight: Int)

fun main(){
    val br = System.`in`.bufferedReader()
    val n = br.readLine().trim().toInt()
    val g = Array(n+1){ mutableListOf<Edge>() }

    repeat(n){
        val st = StringTokenizer(br.readLine())
        val v = st.nextToken().toInt()

        while(true){
            val to = st.nextToken().toInt()
            if(to == -1)break
            val weight = st.nextToken().toInt()
            //양방향
            g[v].add(Edge(to, weight))
            g[to].add(Edge(v, weight))
        }
    }


    fun bfs(start: Int): LongArray{
        val dist = LongArray(n+1){-1L}
        val q = ArrayDeque<Int>()

        dist[start] = 0L
        q.add(start)

        while(q.isNotEmpty()){
            val x = q.removeFirst()
            for((to,weight) in g[x]){
                if(dist[to]==-1L){
                    dist[to] = dist[x] + weight
                    q.add(to)
                }
            }
        }

        return dist
    }

    fun farthest(dist: LongArray): Int{
        var idx = 1
        var best = dist[1]
        for(i in 2..n){
            if(dist[i]>best){
                best = dist[i]
                idx = i
            }
        }

        return idx
    }

    val d1 = bfs(1)
    val a = farthest(d1)

    val d2 = bfs(a)
    var diameter = 0L
    for(i in 1..n) diameter = maxOf(diameter, d2[i-1])

    println(diameter)
}