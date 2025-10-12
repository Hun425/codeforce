package template.advanced

import template.tree.HLD
import template.ds.SegmentTreeLazy

/**
 * ✅ HLD + Lazy 세그트리 예시
 * - 트리의 경로에 구간 덧셈/합 질의를 하는 예제
 * - 실제 문제에 맞게 합 대신 최대/최소 등으로 바꿔도 됩니다.
 */
class HLDPathAddQuery(n: Int, adj: Array<MutableList<Int>>, root: Int = 0) {
    private val hld = HLD(n, adj, root)
    private val seg = SegmentTreeLazy(n)

    fun addPath(u: Int, v: Int, delta: Long) {
        hld.processPath(u, v) { l, r ->
            seg.rangeAdd(l, r, delta); 0L
        }
    }
    fun sumPath(u: Int, v: Int): Long {
        var res = 0L
        hld.processPath(u, v) { l, r ->
            res += seg.rangeSum(l, r); 0L
        }
        return res
    }
}
