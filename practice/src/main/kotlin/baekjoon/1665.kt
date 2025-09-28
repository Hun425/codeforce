package baekjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue


    fun main(){
        val br = BufferedReader(InputStreamReader(System.`in`))
        val t = br.readLine().toInt()



        val minHeap = PriorityQueue<Int>()
        val maxHeap = PriorityQueue<Int>(compareByDescending { it })

        val ans = StringBuilder()

        repeat(t){
            val n = br.readLine().toInt()
            if(maxHeap.isEmpty()|| n<=maxHeap.peek()){
                maxHeap.add(n)
            }else{
                minHeap.add(n)
            }



            if(maxHeap.size < minHeap.size){
                maxHeap.add(minHeap.poll())
            }else if(maxHeap.size> minHeap.size +1){
                minHeap.add(maxHeap.poll())
            }

            if(minHeap.isNotEmpty() && maxHeap.peek() > minHeap.peek()){
                val leftTemp = maxHeap.poll()
                val rightTemp = minHeap.poll()

                maxHeap.add(rightTemp)
                minHeap.add(leftTemp)
            }

            ans.append(maxHeap.peek()).append('\n')
        }

        br.close()

        println(ans.toString())
    }

