package template.flow

/**
 * ✅ 하한/상한이 있는 유량의 표준 변환 스케치
 *
 * [u->v, L..U] 간선을 다음처럼 변환:
 * 1) u->v 용량을 (U-L)로 두고, "수요(demand)" 배열에 demand[u]-=L, demand[v]+=L 반영
 * 2) 초과 수요(+d)는 super-source S에서 해당 노드로 간선(S->node, cap=d)
 *    부족 수요(-d)는 노드에서 super-sink T로 간선(node->T, cap=-d)
 * 3) S->T 최대유량이 모든 양의 수요의 합과 같으면 **feasible flow 존재**
 * 4) 실제 유량은 변환 그래프의 유량에 각 간선마다 L을 더해 복원
 */
object LowerBoundFlowNotes
