package template.string

import java.util.ArrayDeque

/**
 * ✅ 아호-코라식 자동자 (다중 패턴 검색)
 * - Trie + 실패 링크(접미사 링크) + 출력 리스트
 * - 'a'..'z'만 다루는 예시. 다른 문자셋은 맵/배열 크기 변경 필요.
 */
class AhoCorasick {
    private data class Node(val next: IntArray = IntArray(26) { -1 }, var link: Int = -1, var out: MutableList<Int> = mutableListOf())
    private val t = arrayListOf(Node())

    fun addString(s: String, id: Int) {
        var v = 0
        for (ch in s) {
            val c = ch - 'a'
            if (t[v].next[c] == -1) {
                t[v].next[c] = t.size
                t.add(Node())
            }
            v = t[v].next[c]
        }
        t[v].out.add(id)
    }

    fun build() {
        val q: ArrayDeque<Int> = ArrayDeque()
        for (c in 0 until 26) {
            val u = t[0].next[c]
            if (u != -1) { t[u].link = 0; q.add(u) } else t[0].next[c] = 0
        }
        while (q.isNotEmpty()) {
            val v = q.removeFirst()
            val link = t[v].link
            for (c in 0 until 26) {
                val u = t[v].next[c]
                if (u != -1) {
                    t[u].link = t[link].next[c]
                    t[u].out.addAll(t[t[u].link].out) // 실패 링크의 출력도 이어받음
                    q.add(u)
                } else {
                    t[v].next[c] = t[link].next[c]
                }
            }
        }
    }

    /** text에서 (끝 위치, 패턴ID) 목록을 반환 */
    fun findAll(text: String): MutableList<Pair<Int, Int>> {
        val res = mutableListOf<Pair<Int, Int>>()
        var v = 0
        for (i in text.indices) {
            val c = text[i] - 'a'
            v = t[v].next[c]
            for (id in t[v].out) res.add(i to id)
        }
        return res
    }
}
