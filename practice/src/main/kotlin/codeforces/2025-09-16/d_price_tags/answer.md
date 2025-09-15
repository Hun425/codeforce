# D. Price Tags 풀이 과정

## 문제 분석

상점에서 세일을 진행할 때, 모든 가격을 x로 나눈 올림값으로 새로 책정하되, 기존 가격표를 재사용해서 인쇄 비용을 절약하려고 한다. 총 수익(새 가격들의 합 - 가격표 인쇄비용)을 최대화하는 x를 찾는 문제이다.

**핵심 난점:**
- x가 작으면: 새 가격들의 합은 크지만, 가격표 재사용률이 낮음
- x가 크면: 새 가격들의 합은 작지만, 가격표 재사용률이 높을 수 있음
- 단순한 그리디 접근법이 통하지 않음

## 시도한 접근법들

### 1차 시도: 완전 탐색 (시간초과)
```kotlin
// x = 2부터 200,000까지 모든 경우 확인
for (x in 2..200000) {
    val income = calculateIncome(prices, x, y)
    maxIncome = maxOf(maxIncome, income)
}
```

**문제점:**
- 시간복잡도: O(200,000 × n log n) ≈ O(4×10^10) → 너무 느림
- 2초 제한 내에 불가능

### 2차 시도: 상한 축소
```kotlin
// x 상한을 max(max(prices), 500)로 제한
val upperLimit = max(maxPrice, 500)
for (x in 2..upperLimit) { ... }
```

**개선점:**
- 시간복잡도를 O(500 × n log n)로 줄임
- 하지만 여전히 일부 케이스에서 시간초과

### 3차 시도: 의미있는 x만 선별
**아이디어:** 모든 x를 확인하지 말고, ceil(price/x)가 변하는 중요한 지점들만 확인

```kotlin
// 각 가격에 대해 ceil(c/x)가 변하는 x값들 수집
for (price in prices) {
    for (newPrice in 1..price) {
        // ceil(price/x) = newPrice가 되는 x의 범위 계산
        val minX = if (newPrice == price) 1 else (price + newPrice) / (newPrice + 1) + 1
        val maxX = (price + newPrice - 1) / newPrice
        candidateXs.add(minX)
        candidateXs.add(maxX)
    }
}
```

**문제점:**
- 후보 x 생성 로직이 복잡하고 버그 발생
- 여전히 많은 후보가 생성됨

### 4차 시도: 단순화된 후보 선별
```kotlin
// 각 가격의 약수들과 작은 x들을 후보로 선택
for (price in prices) {
    for (x in 2..price + 1) candidateXs.add(x)
    for (div in 1..minOf(price, 1000)) {
        if (price % div == 0) {
            candidateXs.add(div)
            candidateXs.add(div + 1)
        }
    }
}
for (x in 2..100) candidateXs.add(x)
```

**결과:**
- 테스트 케이스는 통과하지만 여전히 시간초과

## 핵심 알고리즘 구성 요소

### 가격표 재사용 계산 (그리디)
```kotlin
val originalSorted = prices.sorted().toMutableList()
val newSorted = newPrices.sorted().toMutableList()

var reusedCount = 0
var i = 0; var j = 0

while (i < originalSorted.size && j < newSorted.size) {
    if (originalSorted[i] == newSorted[j]) {
        reusedCount++; i++; j++
    } else if (originalSorted[i] < newSorted[j]) {
        i++
    } else {
        j++
    }
}
```

### 수익 계산
```kotlin
val totalNewPrice = newPrices.sumOf { it.toLong() }
val newTagsToPrint = prices.size - reusedCount
return totalNewPrice - (newTagsToPrint * y)
```

## 시간 복잡도 분석

### 최종 구현
- **후보 x 개수**: 약 O(sum of prices) + O(divisors) + O(100)
- **각 x에 대한 계산**: O(n log n) (정렬 때문)
- **전체**: 최악의 경우 여전히 너무 클 수 있음

## 미해결 문제점

1. **근본적 시간복잡도 문제**: 아직도 너무 많은 x를 확인함
2. **수학적 최적화 부족**: 최적 x의 특성을 충분히 활용하지 못함
3. **가능한 개선 방향**:
   - x의 상한을 더 작게 (예: sqrt(max_price))
   - 수학적 성질 활용하여 후보 x를 더 효율적으로 선별
   - 이진 탐색이나 삼분 탐색 적용 가능성 검토

## 교훈

1. **완전 탐색의 한계**: 브루트 포스만으로는 시간 제한을 맞추기 어려움
2. **최적화의 중요성**: 후보 선별과 상한 설정이 핵심
3. **수학적 통찰 필요**: 문제의 본질적 특성을 더 깊이 이해해야 함

이 문제는 최적화와 수학적 분석이 더 필요한 고난도 문제였습니다.