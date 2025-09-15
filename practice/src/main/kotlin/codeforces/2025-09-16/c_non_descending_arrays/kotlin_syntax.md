# Kotlin 문법 정리 - C. Non-Descending Arrays 코드 기준

이 문서는 C. Non-Descending Arrays 문제 해결 코드에서 사용된 Kotlin 문법들을 Java와 비교하여 설명합니다.

## 1. mutableMapOf<K, V>() - 가변 Map 생성

### Kotlin
```kotlin
var dp = mutableMapOf<Pair<Int, Int>, Long>()
dp[Pair(a[0], b[0])] = dp.getOrDefault(Pair(a[0], b[0]), 0L) + 1
```

### Java equivalent
```java
Map<Pair<Integer, Integer>, Long> dp = new HashMap<>();
dp.put(new Pair<>(a[0], b[0]), dp.getOrDefault(new Pair<>(a[0], b[0]), 0L) + 1);
```

**설명**: `mutableMapOf()`는 HashMap의 인스턴스를 생성합니다. Kotlin의 Map은 인덱스 연산자(`[]`)를 지원합니다.

## 2. Pair<A, B> - 두 값의 쌍

### Kotlin
```kotlin
val state = Pair(a[0], b[0])
val (lastA, lastB) = state  // 구조 분해 선언
dp[Pair(a[i], b[i])] = count
```

### Java equivalent
```java
// Java에는 기본 Pair가 없으므로 직접 구현하거나 라이브러리 사용
class Pair<A, B> {
    final A first;
    final B second;
    // constructor, equals, hashCode 등...
}

Pair<Integer, Integer> state = new Pair<>(a[0], b[0]);
int lastA = state.first;
int lastB = state.second;
```

**설명**: Kotlin의 `Pair`는 두 값을 묶는 데이터 클래스입니다. 구조 분해 선언으로 값을 쉽게 추출할 수 있습니다.

## 3. 구조 분해 선언 (Destructuring Declaration)

### Kotlin
```kotlin
for ((state, count) in dp) {
    val (lastA, lastB) = state
    // lastA와 lastB 사용
}
```

### Java equivalent
```java
for (Map.Entry<Pair<Integer, Integer>, Long> entry : dp.entrySet()) {
    Pair<Integer, Integer> state = entry.getKey();
    Long count = entry.getValue();
    int lastA = state.first;
    int lastB = state.second;
    // lastA와 lastB 사용
}
```

**설명**: 구조 분해 선언으로 복합 객체의 구성 요소를 개별 변수에 한 번에 할당할 수 있습니다.

## 4. Map.getOrDefault() 함수

### Kotlin
```kotlin
newDp[newState] = (newDp.getOrDefault(newState, 0L) + count) % MOD
```

### Java equivalent
```java
newDp.put(newState, (newDp.getOrDefault(newState, 0L) + count) % MOD);
```

**설명**: 키가 존재하지 않을 때 기본값을 반환하는 함수입니다. Java 8+에서 동일한 함수가 있습니다.

## 5. 범위 표현식과 until

### Kotlin
```kotlin
for (i in 1 until n) {
    // i는 1부터 n-1까지
}

repeat(t) {
    // t번 반복
}
```

### Java equivalent
```java
for (int i = 1; i < n; i++) {
    // i는 1부터 n-1까지
}

for (int i = 0; i < t; i++) {
    // t번 반복
}
```

**설명**: `until`은 끝 값을 포함하지 않는 범위를 나타냅니다.

## 6. 타입 추론과 var/val

### Kotlin
```kotlin
var dp = mutableMapOf<Pair<Int, Int>, Long>()  // var: 가변
val newDp = mutableMapOf<Pair<Int, Int>, Long>()  // val: 불변 참조
val (lastA, lastB) = state  // 타입 자동 추론
```

### Java equivalent
```java
Map<Pair<Integer, Integer>, Long> dp = new HashMap<>();  // 가변
final Map<Pair<Integer, Integer>, Long> newDp = new HashMap<>();  // final
int lastA = state.first;  // 명시적 타입
int lastB = state.second;
```

**설명**:
- `var`: 재할당 가능한 변수
- `val`: 재할당 불가능한 변수 (Java의 final과 유사)

## 7. 확장 함수 - toInt()

### Kotlin
```kotlin
return result.toInt()
```

### Java equivalent
```java
return (int) result;  // 또는 Math.toIntExact(result)
```

**설명**: Long 타입을 Int로 변환하는 확장 함수입니다.

## 8. 논리 연산자

### Kotlin
```kotlin
if (a[i] >= lastA && b[i] >= lastB) {
    // 조건 확인
}
```

### Java equivalent
```java
if (a[i] >= lastA && b[i] >= lastB) {
    // 조건 확인
}
```

**설명**: 논리 연산자는 Java와 동일합니다.

## 9. 모듈러 상수

### Kotlin
```kotlin
private val MOD = 998244353
result = (result + count) % MOD
```

### Java equivalent
```java
private static final int MOD = 998244353;
result = (result + count) % MOD;
```

**설명**: `val`로 선언된 상수는 Java의 `static final`과 유사합니다.

## 10. 컬렉션 순회

### Kotlin
```kotlin
for (count in dp.values) {
    result = (result + count) % MOD
}
```

### Java equivalent
```java
for (Long count : dp.values()) {
    result = (result + count) % MOD;
}
```

**설명**: 컬렉션의 values()를 직접 순회할 수 있습니다.

## 요약

이번 코드에서 주요하게 사용된 Kotlin 문법들:
- **mutableMapOf()**: 가변 맵 생성
- **Pair와 구조 분해**: 두 값의 묶음과 편리한 추출
- **타입 추론**: 간결한 변수 선언
- **확장 함수**: toInt() 등의 편의 함수

Kotlin의 이러한 문법들은 동적 프로그래밍처럼 복잡한 상태를 관리할 때 코드를 더 읽기 쉽고 간결하게 만들어줍니다.