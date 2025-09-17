# Kotlin 문법 정리 - B. Discounts

이 문제에서 사용된 주요 코틀린 문법들을 Java와 비교하여 정리합니다.

## 1. 배열 정렬

### Kotlin
```kotlin
// 내림차순 정렬
a.sortDescending()

// 오름차순 정렬
b.sort()
```

### Java 비교
```java
// 내림차순 정렬
Arrays.sort(a, Collections.reverseOrder());

// 오름차순 정렬
Arrays.sort(b);
```

**차이점**: 코틀린에서는 배열에 직접 `sortDescending()`, `sort()` 메서드를 호출할 수 있어 더 간결합니다.

## 2. 배열 합계

### Kotlin
```kotlin
var totalCost = a.sum()
```

### Java 비교
```java
long totalCost = 0;
for (long value : a) {
    totalCost += value;
}
// 또는 Stream 사용
long totalCost = Arrays.stream(a).sum();
```

**차이점**: 코틀린의 `sum()` 확장 함수는 배열의 모든 요소를 더하는 간단한 방법을 제공합니다.

## 3. 향상된 for 루프

### Kotlin
```kotlin
for (voucherSize in b) {
    // voucherSize 사용
}
```

### Java 비교
```java
for (int voucherSize : b) {
    // voucherSize 사용
}
```

**차이점**: 문법이 거의 유사하지만, 코틀린에서는 `in` 키워드를 사용합니다.

## 4. 변수 선언

### Kotlin
```kotlin
var totalCost = a.sum()    // 가변 변수
var productPtr = 0         // 가변 변수
val voucherSize = b[i]     // 불변 변수 (지역 스코프)
```

### Java 비교
```java
long totalCost = a.sum();  // 가변 변수
int productPtr = 0;        // 가변 변수
final int voucherSize = b[i]; // 불변 변수
```

**차이점**:
- `var`: 가변 변수 (Java의 일반 변수)
- `val`: 불변 변수 (Java의 `final` 변수)
- 코틀린은 타입 추론으로 타입 생략 가능

## 5. 조건문과 범위 검사

### Kotlin
```kotlin
if (productPtr + voucherSize <= n) {
    // 로직
}
```

### Java 비교
```java
if (productPtr + voucherSize <= n) {
    // 로직
}
```

**차이점**: 기본적인 조건문은 Java와 동일합니다.

## 6. 패키지 선언 (백틱 사용)

### Kotlin
```kotlin
package codeforces.`2025-09-17`.b_discounts
```

### Java 비교
```java
// Java에서는 숫자로 시작하는 패키지명 불가
// 대안: package codeforces.y2025_09_17.b_discounts
```

**차이점**: 코틀린에서는 백틱(`)을 사용하여 숫자로 시작하는 식별자나 키워드를 식별자로 사용할 수 있습니다.

## 7. 함수 정의

### Kotlin
```kotlin
fun solve(n: Int, k: Int, a: LongArray, b: IntArray): Long {
    // 함수 본문
    return totalCost
}
```

### Java 비교
```java
public long solve(int n, int k, long[] a, int[] b) {
    // 함수 본문
    return totalCost;
}
```

**차이점**:
- `fun` 키워드로 함수 선언
- 매개변수: `이름: 타입` 순서
- 반환 타입: 함수명 뒤에 `: 타입`으로 명시
- 접근 제한자 생략 시 기본적으로 `public`

## 8. 주요 타입

### Kotlin vs Java
| Kotlin | Java |
|--------|------|
| `Int` | `int` |
| `Long` | `long` |
| `LongArray` | `long[]` |
| `IntArray` | `int[]` |

**차이점**: 코틀린에서는 원시 타입도 객체로 취급되며, 배열은 전용 클래스(`IntArray`, `LongArray` 등)를 사용합니다.

## 9. 표준 입력 처리

### Kotlin
```kotlin
fun main() {
    val t = readln().toInt()
    repeat(t) {
        val (n, k) = readln().split(" ").map { it.toInt() }
        val a = readln().split(" ").map { it.toLong() }.toLongArray()
        val b = readln().split(" ").map { it.toInt() }.toIntArray()
        println(solution.solve(n, k, a, b))
    }
}
```

### Java 비교
```java
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();
    for (int i = 0; i < t; i++) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        long[] a = new long[n];
        for (int j = 0; j < n; j++) {
            a[j] = sc.nextLong();
        }
        int[] b = new int[k];
        for (int j = 0; j < k; j++) {
            b[j] = sc.nextInt();
        }
        System.out.println(solution.solve(n, k, a, b));
    }
}
```

**차이점**:
- `readln()`: 한 줄 읽기
- `split()`, `map()`, `toIntArray()`: 함수형 프로그래밍 스타일
- `repeat()`: 반복문의 간결한 표현
- 구조 분해 할당: `val (n, k) = ...`