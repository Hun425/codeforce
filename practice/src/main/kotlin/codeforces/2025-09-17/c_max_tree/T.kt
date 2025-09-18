package codeforces.`2025-09-17`.c_max_tree

fun main(){
    val t = readln().toInt()

    repeat(t){
        solved()
    }
}

fun solved(){
    val n = readln().toInt()

    val adj = Array(n+1){mutableListOf<Int>()}

    val inDegree = IntArray(n+1)

    repeat(n-1){
        val (u,v,x,y) = readln().split(" ").map { it.toInt() }

        if(x>y){
            adj[v].add(u)
            inDegree[u]++
        }else{
            adj[u].add(v)
            inDegree[v]++
        }
    }

    val que = ArrayDeque<Int>()
    val startingNodes = (1..n).filter{ inDegree[it] == 0}
    que.addAll(startingNodes)

    val tpOrder = mutableListOf<Int>()

    while(que.isNotEmpty()){
        val u = que.removeFirst()
        tpOrder.add(u)

        adj[u].forEach{ v ->
            inDegree[v]--
            if(inDegree[v]==0){
                que.add(v)
            }
        }

    }

    val ans = IntArray(n+1)

    tpOrder.forEachIndexed { index, node ->
        ans[node] = index+1
    }

    println(ans.slice(1..n).joinToString(" "))
}