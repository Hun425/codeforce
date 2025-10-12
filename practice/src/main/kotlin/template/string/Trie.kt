package template.string

/**
 * ✅ Trie (사전 트리)
 * - 문자 경로대로 내려가며 삽입/조회
 */
class Trie {
    private data class Node(val next: MutableMap<Char, Int> = HashMap(), var end: Boolean = false)
    private val nodes = arrayListOf(Node())

    fun insert(s: String) {
        var cur = 0
        for (ch in s) {
            val nxt = nodes[cur].next.getOrPut(ch) { nodes.add(Node()); nodes.size - 1 }
            cur = nxt
        }
        nodes[cur].end = true
    }
    fun contains(s: String): Boolean {
        var cur = 0
        for (ch in s) {
            val nxt = nodes[cur].next[ch] ?: return false
            cur = nxt
        }
        return nodes[cur].end
    }
}
