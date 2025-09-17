# A. All Lengths Subtraction 문제 풀이 해설

## 1. 문제 분석

이 문제는 `k=1`부터 `n`까지의 길이를 갖는 부분 배열을 정확히 한 번씩 선택하여 1씩 뺐을 때, 최종적으로 주어진 순열 `p`의 모든 원소를 0으로 만들 수 있는지 판별하는 문제입니다.

핵심은 **`i`번째 원소 `p[i]`가 최종적으로 0이 되려면, `i`번째 위치를 포함하는 부분 배열이 정확히 `p[i]`번 선택되어야 한다**는 점입니다.

연산의 순서는 최종 결과에 영향을 주지 않으므로, 우리는 각 길이 `k`에 대해 어떤 시작 위치의 부분 배열을 선택할지만 결정하면 됩니다.

## 2. 핵심 아이디어 및 알고리즘 설계

단순히 모든 경우의 수를 탐색하는 것은 불가능하므로, 문제의 구조를 파고들어 결정적인 규칙을 찾아야 합니다. '탐욕적(Greedy)' 접근법이 유효한 해결책이 될 수 있습니다.

### 2.1. 관계식 도출

- `C_i`: `i`번째 원소가 총 몇 번의 뺄셈 연산을 당했는지 나타내는 횟수. 우리의 목표는 모든 `i`에 대해 `C_i = p[i]`를 만족시키는 것입니다.
- `S_i`: `i`번째 위치에서 **시작**하는 부분 배열의 개수.
- `E_i`: `i`번째 위치에서 **끝**나는 부분 배열의 개수.

인접한 두 원소 `C_{i-1}`과 `C_i`의 관계를 생각해봅시다. `i`번째 원소가 뺄셈을 당하는 경우는 `i-1`번째 원소도 함께 당하는 경우(부분 배열이 `i-1`과 `i`를 모두 포함)와, `i`에서 새로 시작하는 경우를 더하고, `i-1`에서 끝나는 경우를 제외한 것과 같습니다.

이를 식으로 표현하면 다음과 같습니다:
`C_i = C_{i-1} + S_i - E_{i-1}`

우리의 목표 `C_i = p[i]`를 이 식에 대입하면 `S_i`를 계산할 수 있습니다:
`p[i] = p[i-1] + S_i - E_{i-1}`
`S_i = p[i] - p[i-1] + E_{i-1}`
(단, `p[-1]`은 0으로 간주합니다.)

이 식을 통해 우리는 각 위치 `i`에서 몇 개의 부분 배열을 시작해야 하는지(`S_i`)를 순차적으로 계산할 수 있습니다.

### 2.2. 탐욕적(Greedy) 전략

이제 `i=0`부터 `n-1`까지 순회하면서 각 단계에서 `S_i`개의 부분 배열을 시작시켜야 합니다. 이때 어떤 길이(`k`)의 부분 배열을 선택해야 할까요?

**핵심 전략**: 현재 위치 `i`에서 시작할 수 있는 길이들 중에서 **가장 긴 것부터** 우선적으로 사용합니다.

**이유**: 길이가 긴 부분 배열은 시작할 수 있는 위치가 매우 제한적입니다. 예를 들어, 길이 `n`의 부분 배열은 인덱스 0에서만 시작할 수 있습니다. 반면, 길이가 짧은 부분 배열은 상대적으로 더 많은 위치에서 시작할 수 있어 유연합니다. 따라서, 나중 단계(더 큰 `i`값)를 위해 유연한 짧은 길이들을 남겨두고, 지금 당장 사용해야만 하는 제약이 심한 긴 길이들을 먼저 소진하는 것이 최적의 전략입니다.

### 2.3. 알고리즘 흐름

1.  `availableLengths` 배열을 만들어 `1`부터 `n`까지의 모든 길이를 사용할 수 있다고 표시합니다.
2.  `endsCountAt` 배열을 만들어 각 위치에서 끝나는 연산의 수를 기록합니다.
3.  `i`를 `0`부터 `n-1`까지 순회합니다.
    a. `S_i = p[i] - p[i-1] + E_{i-1}` 공식을 이용해 현재 위치 `i`에서 시작해야 하는 부분 배열의 개수(`mustStartCount`)를 계산합니다.
    b. `mustStartCount`가 음수이면 불가능하므로 "NO"를 반환합니다.
    c. `mustStartCount` 만큼의 부분 배열을 할당해야 합니다. 현재 위치 `i`에서 시작 가능한 가장 긴 길이(`n-i`)부터 1까지 역순으로 순회하며, `availableLengths`에 사용 가능한 길이가 있다면 선택합니다.
    d. 선택된 길이 `k`는 `availableLengths`에서 제거하고, 이 연산이 끝나는 위치 `i+k-1`에 대해 `endsCountAt[i+k-1]` 값을 1 증가시킵니다.
    e. 만약 `mustStartCount` 만큼의 길이를 모두 할당하지 못하면, 불가능하므로 "NO"를 반환합니다.
4.  모든 `i`에 대한 순회가 성공적으로 끝나면, 가능한 경우이므로 "YES"를 반환합니다.

이 알고리즘의 시간 복잡도는 `O(n^2)`이며, `n <= 100` 제약 조건 하에 충분히 효율적입니다.

## 3. 최종 코드 구현 (Solution.kt)

```kotlin
package codeforces.`2025-09-17`.a_all_lengths_subtraction

class Solution {
    fun solve(n: Int, p: IntArray): String {
        val pExt = LongArray(n + 1)
        for (i in 0 until n) {
            pExt[i + 1] = p[i].toLong()
        }

        val availableLengths = BooleanArray(n + 1) { it > 0 }
        val endsCountAt = IntArray(n)

        for (i in 0 until n) {
            val prevP = pExt[i]
            val currentP = pExt[i + 1]
            val endedBefore = if (i > 0) endsCountAt[i - 1] else 0

            val mustStartCount = currentP - prevP + endedBefore

            if (mustStartCount < 0) {
                return "NO"
            }

            var needed = mustStartCount
            if (needed > 0) {
                for (k in (n - i) downTo 1) {
                    if (needed == 0L) break
                    if (availableLengths[k]) {
                        availableLengths[k] = false
                        val endIndex = i + k - 1
                        endsCountAt[endIndex]++
                        needed--
                    }
                }
            }

            if (needed > 0) {
                return "NO"
            }
        }

        return "YES"
    }
}
```
