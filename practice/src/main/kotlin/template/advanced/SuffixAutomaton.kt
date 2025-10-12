package template.advanced

/**
 * ✅ 접미사 자동자(SAM)
 * - 각 상태는 "끝나는 위치가 같은 모든 부분문자열의 등가 클래스"를 표현
 * - s를 한 글자씩 extend 하며 상태/링크를 유지
 * - 서로 다른 부분문자열 개수 = sum(len[v] - len[link[v]])
 */
class SuffixAutomaton {
    data class State(var next: HashMap<Char, Int> = HashMap(), var link: Int = -1, var len: Int = 0)
    private val st = arrayListOf(State())
    private var last = 0

    fun extend(c: Char) {
        var cur = st.size
        st.add(State(hashMapOf(), 0, st[last].len + 1))
        var p = last

        // 1) 뒤로 타고 올라가며 없는 간선을 c로 연결
        while (p >= 0 && !st[p].next.containsKey(c)) {
            st[p].next[c] = cur
            p = st[p].link
        }

        if (p == -1) {
            // 루트에 연결
            st[cur].link = 0
        } else {
            val q = st[p].next[c]!!
            if (st[p].len + 1 == st[q].len) {
                // 바로 연결 가능
                st[cur].link = q
            } else {
                // 2) clone 상태로 분기 → 자동자 특성 유지
                val clone = st.size
                st.add(State(HashMap(st[q].next), st[q].link, st[p].len + 1))
                while (p >= 0 && st[p].next[c] == q) {
                    st[p].next[c] = clone
                    p = st[p].link
                }
                st[q].link = clone
                st[cur].link = clone
            }
        }
        last = cur
    }

    fun build(s: String) { for (ch in s) extend(ch) }

    /** 서로 다른 부분문자열 개수 */
    fun countDistinct(): Long {
        var ans = 0L
        for (i in 1 until st.size) ans += (st[i].len - st[st[i].link].len)
        return ans
    }
}
