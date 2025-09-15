# Kotlin 문법 정리 - B. Maximum Cost Permutation 코드 기준

이 문서는 B. Maximum Cost Permutation 문제 해결 코드에서 사용된 Kotlin 문법들을 Java와 비교하여 설명합니다.

## 1. mutableSetOf<T>() - 가변 Set 생성

### Kotlin
```kotlin
val missing = mutableSetOf<Int>()
for (i in 1..n) {
    missing.add(i)
}
missing.remove(p[i])
```

### Java equivalent
```java
Set<Integer> missing = new HashSet<>();
for (int i = 1; i <= n; i++) {
    missing.add(i);
}
missing.remove(p[i]);
```

**설명**: `mutableSetOf()`는 HashSet의 인스턴스를 생성합니다. 중복을 허용하지 않는 컬렉션입니다.

## 2. 범위 표현식 (Range Expressions)

### Kotlin
```kotlin
for (i in 1..n) {          // 1부터 n까지 (inclusive)
    missing.add(i)
}

for (i in 0 until n) {     // 0부터 n-1까지 (exclusive)
    if (p[i] > 0) {
        missing.remove(p[i])
    }
}

for (i in n - 1 downTo 0) { // n-1부터 0까지 (역순)
    // ...
}
```

### Java equivalent
```java
for (int i = 1; i <= n; i++) {          // 1..n
    missing.add(i);
}

for (int i = 0; i < n; i++) {           // 0 until n
    if (p[i] > 0) {
        missing.remove(p[i]);
    }
}

for (int i = n - 1; i >= 0; i--) {      // downTo
    // ...
}
```

**설명**:
- `1..n`: 1부터 n까지 포함
- `0 until n`: 0부터 n-1까지
- `n-1 downTo 0`: 역순으로 n-1부터 0까지

## 3. IntArray 생성자와 람다

### Kotlin
```kotlin
val p = IntArray(n) { scanner.nextInt() }
```

### Java equivalent
```java
int[] p = new int[n];
for (int i = 0; i < n; i++) {
    p[i] = scanner.nextInt();
}
```

**설명**: 배열 생성과 동시에 초기화할 수 있습니다. 람다의 파라미터(인덱스)는 생략 가능합니다.

## 4. repeat() 함수

### Kotlin
```kotlin
repeat(t) {
    val n = scanner.nextInt()
    val p = IntArray(n) { scanner.nextInt() }
    println(solution.solve(n, p))
}
```

### Java equivalent
```java
for (int i = 0; i < t; i++) {
    int n = scanner.nextInt();
    int[] p = new int[n];
    for (int j = 0; j < n; j++) {
        p[j] = scanner.nextInt();
    }
    System.out.println(solution.solve(n, p));
}
```

**설명**: `repeat(횟수) { 블록 }`은 지정된 횟수만큼 블록을 실행합니다.

## 5. Set.contains() 메서드

### Kotlin
```kotlin
if (missing.contains(neededValue) && missing.size > 1) {
    // 다른 선택지가 있으므로 접두사를 끊을 수 있음
    break
}
```

### Java equivalent
```java
if (missing.contains(neededValue) && missing.size() > 1) {
    // 다른 선택지가 있으므로 접두사를 끊을 수 있음
    break;
}
```

**설명**: Kotlin에서 Set의 크기는 `size` 프로퍼티로, Java에서는 `size()` 메서드로 접근합니다.

## 6. Set.toMutableSet() 확장 함수

### Kotlin
```kotlin
val remainingMissing = missing.toMutableSet()
```

### Java equivalent
```java
Set<Integer> remainingMissing = new HashSet<>(missing);
```

**설명**: 기존 Set을 복사하여 새로운 가변 Set을 만듭니다.

## 7. 타입 추론

### Kotlin
```kotlin
val missing = mutableSetOf<Int>()  // MutableSet<Int> 타입으로 추론
var minLeftPrefix = 0              // Int 타입으로 추론
val neededValue = i + 1            // Int 타입으로 추론
```

### Java equivalent
```java
Set<Integer> missing = new HashSet<>();
int minLeftPrefix = 0;
int neededValue = i + 1;
```

**설명**: Kotlin은 초기값을 보고 타입을 자동으로 추론합니다.
- `val`: 불변 변수 (Java의 final)
- `var`: 가변 변수

## 8. 함수 정의

### Kotlin
```kotlin
fun solve(n: Int, p: IntArray): Int {
    // 누락된 숫자들 찾기
    val missing = mutableSetOf<Int>()
    // ...
    return n - minLeftPrefix - minRightSuffix
}
```

### Java equivalent
```java
public int solve(int n, int[] p) {
    // 누락된 숫자들 찾기
    Set<Integer> missing = new HashSet<>();
    // ...
    return n - minLeftPrefix - minRightSuffix;
}
```

**설명**:
- `fun` 키워드로 함수 선언
- 파라미터는 `이름: 타입` 형식
- 반환 타입은 `: 타입`으로 명시

## 9. 논리 연산과 조건문

### Kotlin
```kotlin
if (missing.contains(neededValue) && missing.size > 1) {
    break
} else if (missing.contains(neededValue)) {
    minLeftPrefix++
    missing.remove(neededValue)
} else {
    break
}
```

### Java equivalent
```java
if (missing.contains(neededValue) && missing.size() > 1) {
    break;
} else if (missing.contains(neededValue)) {
    minLeftPrefix++;
    missing.remove(neededValue);
} else {
    break;
}
```

**설명**: 논리 연산자와 조건문은 Java와 거의 동일하지만 세미콜론이 불필요합니다.

## 10. 주석과 가독성

### Kotlin
```kotlin
// 1단계: 누락된 숫자들 찾기
val missing = mutableSetOf<Int>()

// 2단계: 왼쪽 접두사 최소 길이 계산
var minLeftPrefix = 0

// 이미 올바른 위치에 있음
minLeftPrefix++
```

**설명**: Kotlin은 간결한 문법 덕분에 주석과 코드가 더 읽기 쉽습니다.

## 요약

이번 코드에서 주요하게 사용된 Kotlin 문법들:
- **mutableSetOf()**: 가변 Set 생성
- **범위 표현식**: `1..n`, `0 until n`, `downTo`
- **타입 추론**: `val`/`var`로 간결한 변수 선언
- **확장 함수**: `toMutableSet()` 같은 편리한 변환 메서드

Kotlin의 이러한 문법들은 Java보다 더 간결하고 표현력이 뛰어나며, 특히 컬렉션 조작에서 매우 편리합니다.