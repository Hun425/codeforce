## `c_max_tree` 문제 풀이에 사용된 코틀린 문법

이 문서는 `Solution.kt` 파일에 사용된 주요 코틀린 문법을 Java와 비교하여 설명합니다.

### 1. 한 줄 입력받아 여러 변수에 할당하기 (Destructuring Declaration)

`Solution.kt`에서는 한 줄의 입력을 공백으로 나누어 여러 변수에 한 번에 할당합니다.

**Kotlin 코드:**
```kotlin
val (u, v, x, y) = readln().split(" ").map { it.toInt() }
```

- **`readln()`**: 표준 입력에서 한 줄을 읽어 문자열로 반환합니다. (Java의 `BufferedReader.readLine()`)
- **`split(" ")`**: 문자열을 공백 기준으로 나누어 `List<String>`을 생성합니다.
- **`map { it.toInt() }`**: 리스트의 각 문자열(`it`)을 `Int` 타입으로 변환하여 새로운 `List<Int>`를 생성합니다.
- **`val (u, v, x, y) = ...`**: 리스트의 각 원소를 순서대로 `u`, `v`, `x`, `y` 변수에 할당합니다. 이를 **구조 분해 할당(Destructuring Declaration)**이라고 합니다.

**Java 코드 (유사 동작):**
```java
Scanner scanner = new Scanner(System.in);
String[] parts = scanner.nextLine().split(" ");
int u = Integer.parseInt(parts[0]);
int v = Integer.parseInt(parts[1]);
int x = Integer.parseInt(parts[2]);
int y = Integer.parseInt(parts[3]);
```
코틀린을 사용하면 훨씬 간결하고 직관적인 코드 작성이 가능합니다.

### 2. 배열 및 리스트 초기화

인접 리스트와 진입 차수 배열을 초기화하는 코드입니다.

**Kotlin 코드:**
```kotlin
// 람다를 사용하여 각 원소가 빈 MutableList인 크기 n+1의 배열 생성
val adj = Array(n + 1) { mutableListOf<Int>() }

// 0으로 초기화된 크기 n+1의 IntArray 생성
val inDegree = IntArray(n + 1)
```

- `Array(size) { ... }`: 지정된 크기의 배열을 만들고, 각 원소를 중괄호 `{}` 안의 람다 함수가 반환하는 값으로 초기화합니다.
- `IntArray(size)`: 모든 원소가 `0`으로 초기화된 정수형 배열을 생성하는 간결한 방법입니다.

**Java 코드 (유사 동작):**
```java
// 인접 리스트 초기화
List<Integer>[] adj = new ArrayList[n + 1];
for (int i = 0; i <= n; i++) {
    adj[i] = new ArrayList<>();
}

// 진입 차수 배열 초기화 (기본적으로 0으로 초기화됨)
int[] inDegree = new int[n + 1];
```

### 3. 범위 기반 for 루프

코틀린은 `..` 연산자를 사용하여 간결한 범위 표현이 가능합니다.

**Kotlin 코드:**
```kotlin
for (i in 1..n) {
    // 1부터 n까지 (n 포함) 반복
}
```

**Java 코드 (유사 동작):**
```java
for (int i = 1; i <= n; i++) {
    // 1부터 n까지 (n 포함) 반복
}
```

### 4. 컬렉션 처리 및 출력 (`slice`, `joinToString`)

결과 순열 배열 `p`의 일부를 잘라내어 공백으로 구분된 문자열로 만드는 코드입니다.

**Kotlin 코드:**
```kotlin
// 배열 p의 1번 인덱스부터 n번 인덱스까지의 원소를 잘라내어(slice)
// 공백(" ")으로 연결하여(joinToString) 한 줄로 출력한다.
println(p.slice(1..n).joinToString(" "))
```

- **`slice(1..n)`**: 배열이나 리스트에서 특정 범위의 원소들을 추출하여 새로운 리스트를 만듭니다.
- **`joinToString(" ")`**: 컬렉션의 각 원소를 주어진 구분자(여기서는 공백)로 연결하여 하나의 문자열로 만듭니다.

**Java 코드 (유사 동작):**
```java
// Java 8 스트림 API 사용
import java.util.stream.Collectors;
import java.util.Arrays;

// ...

String result = Arrays.stream(p, 1, n + 1) // p 배열의 1번 인덱스부터 n번 인덱스까지
                      .mapToObj(String::valueOf)
                      .collect(Collectors.joining(" "));
System.out.println(result);

// 또는 일반적인 for 루프 사용
StringBuilder sb = new StringBuilder();
for (int i = 1; i <= n; i++) {
    sb.append(p[i]);
    if (i < n) {
        sb.append(" ");
    }
}
System.out.println(sb.toString());
```
코틀린의 표준 라이브러리는 데이터 처리를 위한 강력하고 간결한 함수들을 많이 제공합니다.
