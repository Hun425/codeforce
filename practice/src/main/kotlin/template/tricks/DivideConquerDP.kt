package template.tricks

/**
 * ✅ 분할정복 DP 최적화 스켈레톤
 * - dp[k][i] = min_{j < i} ( dp[k-1][j] + C(j, i) )
 * - 최적 j(=opt)가 단조 증가(콘벡스 조건 등)일 때 O(KN log N) 내로 최적화 가능
 */
object DivideConquerDPSkeleton {
    // 문제별 C(j,i)와 opt 단조성 증명을 확인하고 구현하세요.
}
