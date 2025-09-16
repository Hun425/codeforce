# D. Price Tags 문제 풀이 해설

## 1. 문제 분석

이 문제는 총수익을 최대로 만드는 최적의 정수 계수 `x > 1`를 찾는 것입니다.

- **총수익** = (모든 상품의 새 가격 합) - (새로 인쇄한 가격표의 총비용)
- **새 가격**: `ceil(기존 가격 / x)`
- **가격표 재사용**: 특정 가격 `p`에 대해, 새 가격이 `p`인 상품의 수가 기존 가격이 `p`였던 상품의 수보다 많을 때만 그 차이만큼 새로 인쇄합니다.

가장 큰 난관은 `x`의 범위가 정해져 있지 않다는 점입니다. 모든 `x`를 시험하는 것은 불가능해 보입니다.

## 2. 핵심 아이디어 및 알고리즘 설계

### 2.1. `x`의 탐색 범위 최적화

`x`가 상품의 최대 가격(`C_max`)보다 커지면, 모든 상품의 새 가격은 `1`이 됩니다. `x`가 `C_max + 1`일 때와 `C_max + 2`일 때의 새 가격은 모두 `1`로 동일하므로, `x`를 `2`부터 `C_max + 1`까지만 검사하면 모든 가능한 경우를 확인할 수 있습니다.

하지만 `C_max`는 최대 `2 * 10^5`이므로, `x`를 순회하며 매번 `n`개의 상품 가격을 다시 계산하는 `O(n * C_max)` 방식은 시간 초과입니다.

### 2.2. 누적 합을 이용한 이익 계산 가속화

`x`가 주어졌을 때, 총수익을 `O(n)`보다 빠르게 계산할 방법이 필요합니다.

핵심은 **상품 중심이 아닌, 가격 중심으로 계산 방식을 전환**하는 것입니다.

1.  **전처리 (가격 분포 계산)**
    - `counts[v]`: 가격이 `v`인 상품의 개수를 저장하는 배열
    - `prefixCounts[v]`: 가격이 `v` 이하인 상품의 누적 개수를 저장하는 배열 (`prefixCounts[i+1] = prefixCounts[i] + counts[i]`)

    이 `prefixCounts` 배열을 사용하면, 특정 가격 범위 `(A, B]`에 속하는 상품의 개수를 `prefixCounts[B+1] - prefixCounts[A+1]` (인덱스 조정에 따라) 로 `O(1)`에 계산할 수 있습니다.

2.  **효율적인 이익 계산**
    - `x`를 `2`부터 `C_max + 1`까지 순회합니다.
    - 각 `x`에 대해, 가능한 모든 **새 가격 `p`** (`1, 2, 3, ...`)를 기준으로 계산을 수행합니다.
    - 새 가격이 `p`가 되는 기존 가격 `c`의 범위는 `p-1 < c / x <= p`, 즉 `(p-1) * x < c <= p * x` 입니다.
    - `prefixCounts` 배열을 이용하면, 이 범위에 해당하는 상품 수(`numItems`)를 `O(1)`에 구할 수 있습니다.
    - `numItems`를 구했다면,
        - 새 가격의 합에 `p * numItems`를 더합니다.
        - 새로 인쇄할 가격표 수는 `max(0, numItems - counts[p])` 입니다. (기존 가격이 `p`인 상품 수 `counts[p]` 만큼 재사용)
    - 모든 `p`에 대해 이 과정을 반복하면, 해당 `x`의 총수익을 빠르게 계산할 수 있습니다.

## 3. 시간 및 공간 복잡도 분석

- **시간 복잡도**: `O(C_max * log(C_max))`
    - 전처리 과정은 `O(C_max)` 입니다.
    - 메인 루프는 `x`가 `2`부터 `C_max+1`까지 순회합니다.
    - 내부 루프(새 가격 `p` 기준)는 약 `C_max / x` 번 반복됩니다.
    - 따라서 총 연산량은 `Σ(C_max / x)` (x=2 to C_max+1) 이며, 이는 조화 급수(Harmonic series)의 형태로 근사적으로 `O(C_max * log(C_max))`가 됩니다.
    - `C_max`가 `2 * 10^5`일 때, 이 방법은 시간 제한 내에 충분히 동작합니다.

- **공간 복잡도**: `O(C_max)`
    - `counts`와 `prefixCounts` 배열을 위해 `O(C_max)`의 공간이 필요하며, 이는 메모리 제한을 만족합니다.

## 4. 최종 코드 구현 (Solution.kt)

```kotlin
package codeforces.`2025-09-16`.d_price_tags

import kotlin.math.max
import kotlin.math.min

class Solution {
    fun solve(n: Int, y: Long, prices: IntArray): Long {
        val maxPrice = prices.maxOrNull() ?: 0
        if (maxPrice == 0) {
            return 0
        }

        // 1. 전처리: 가격별 개수 및 누적 합 계산
        val counts = LongArray(maxPrice + 1)
        for (price in prices) {
            counts[price]++
        }

        val prefixCounts = LongArray(maxPrice + 2)
        for (i in 0..maxPrice) {
            prefixCounts[i + 1] = prefixCounts[i] + counts[i]
        }

        var maxProfit = Long.MIN_VALUE

        // 2. x를 2부터 maxPrice + 1까지 순회하며 최적의 x 탐색
        for (x in 2..maxPrice + 1) {
            var currentTotalValue = 0L
            var tagsToPrint = 0L

            // 3. 새 가격 p를 기준으로 이익 계산
            var p = 1
            while (true) {
                // 새 가격이 p가 되는 기존 가격의 범위: ( (p-1)*x, p*x ]
                val startRange = (p - 1L) * x + 1
                if (startRange > maxPrice) {
                    break // 탐색 범위를 넘어서면 종료
                }
                val endRange = p.toLong() * x

                // 누적 합 배열을 사용하여 해당 범위의 상품 개수를 O(1)에 계산
                val numItems = prefixCounts[min(endRange, maxPrice.toLong()).toInt() + 1] - prefixCounts[startRange.toInt()]

                if (numItems > 0) {
                    // 새 가격의 총합
                    currentTotalValue += p.toLong() * numItems
                    // 재사용할 수 없는, 새로 인쇄해야 하는 가격표의 수
                    val originalCountP = if (p <= maxPrice) counts[p] else 0
                    tagsToPrint += max(0, numItems - originalCountP)
                }
                p++
            }

            val currentProfit = currentTotalValue - tagsToPrint * y
            if (currentProfit > maxProfit) {
                maxProfit = currentProfit
            }
        }

        return maxProfit
    }
}
```
