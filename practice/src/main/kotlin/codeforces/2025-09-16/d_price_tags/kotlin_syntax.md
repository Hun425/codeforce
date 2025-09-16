# D. Price Tags 문제 풀이에 사용된 코틀린 문법

이 문제의 풀이 과정에서는 효율적인 계산을 위해 배열과 반복문을 적극적으로 활용했습니다. Java와 비교하여 주요 코틀린 문법을 정리합니다.

### 1. 엘비스 연산자 (Elvis Operator) `?:`

Nullable한 값을 다룰 때, `null`일 경우 사용할 기본값을 지정하는 간결한 방법을 제공합니다.

- **Kotlin 코드**
  ```kotlin
  val maxPrice = prices.maxOrNull() ?: 0
  ```
  `prices.maxOrNull()`은 배열이 비어있으면 `null`을 반환합니다. 이때 엘비스 연산자 `?:`는 `null` 대신 `0`을 `maxPrice`에 할당하도록 합니다.

- **Java 비교**
  ```java
  // Java 8 Optional을 사용한 유사한 로직
  int maxPrice = Arrays.stream(prices).max().orElse(0);

  // 또는 전통적인 if문
  Integer maxVal = ...; // 최대값을 찾는 로직
  int maxPrice = (maxVal != null) ? maxVal : 0;
  ```

### 2. 범위 지정 연산자 `..`

`for` 루프에서 특정 범위의 숫자를 순회할 때 사용되어 코드를 더 읽기 쉽게 만듭니다.

- **Kotlin 코드**
  ```kotlin
  for (x in 2..maxPrice + 1) {
      // ...
  }
  ```
  `2`부터 `maxPrice + 1`까지의 모든 정수를 포함하는 범위를 생성하여 `x`가 순서대로 해당 값들을 갖게 됩니다.

- **Java 비교**
  ```java
  for (int x = 2; x <= maxPrice + 1; x++) {
      // ...
  }
  ```

### 3. `if-else` 표현식 (Expression)

코틀린에서 `if-else`는 문장(statement)일 뿐만 아니라 값을 반환하는 표현식(expression)으로도 사용될 수 있습니다. 이는 Java의 삼항 연산자와 유사한 간결함을 제공합니다.

- **Kotlin 코드**
  ```kotlin
  val originalCountP = if (p <= maxPrice) counts[p] else 0
  ```
  조건 `p <= maxPrice`가 참이면 `counts[p]`가, 거짓이면 `0`이 `originalCountP` 변수에 할당됩니다.

- **Java 비교 (삼항 연산자)**
  ```java
  long originalCountP = (p <= maxPrice) ? counts[p] : 0;
  ```

### 4. 원시 타입 배열 (Primitive Type Arrays)

코틀린은 `IntArray`, `LongArray`, `DoubleArray` 등 박싱(boxing) 오버헤드 없이 Java의 원시 타입 배열(`int[]`, `long[]`)처럼 동작하는 특별한 배열 클래스를 제공합니다.

- **Kotlin 코드**
  ```kotlin
  val counts = LongArray(maxPrice + 1)
  val prefixCounts = LongArray(maxPrice + 2)
  ```

- **Java 비교**
  ```java
  long[] counts = new long[maxPrice + 1];
  long[] prefixCounts = new long[maxPrice + 2];
  ```
  만약 코틀린에서 `Array<Long>`을 사용했다면, 이는 Java의 `Long[]`에 해당하며, 원시 타입이 아닌 객체 타입으로 처리되어 성능 저하가 발생할 수 있습니다.

### 5. 명시적 타입 변환

코틀린은 Java와 달리 숫자 타입 간의 암시적 형 변환(implicit conversion)을 허용하지 않습니다. 모든 타입 변환은 `.toInt()`, `.toLong()` 등과 같은 함수를 통해 명시적으로 이루어져야 합니다.

- **Kotlin 코드**
  ```kotlin
  val endRange = p.toLong() * x // p(Int)를 Long으로 변환하여 오버플로우 방지
  val numItems = prefixCounts[min(endRange, maxPrice.toLong()).toInt() + 1] - ...
  ```

- **Java 비교**
  ```java
  // Java에서는 int와 long의 연산 시 int가 long으로 자동 형 변환됨
  long endRange = (long)p * x; 
  ```
  코틀린의 명시적 변환 정책은 개발자의 의도를 명확하게 하고 잠재적인 버그를 줄이는 데 도움이 됩니다.
