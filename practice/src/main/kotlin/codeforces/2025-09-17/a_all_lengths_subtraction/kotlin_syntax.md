# A. All Lengths Subtraction 문제 풀이에 사용된 코틀린 문법

이번 풀이에서는 순차적인 탐색과 상태 관리를 위해 배열과 반복문을 중심으로 코드를 작성했습니다. Java와 비교하여 주요 코틀린 문법을 정리합니다.

### 1. 배열 생성과 초기화 (람다 활용)

코틀린은 배열을 생성할 때, 각 인덱스의 초기값을 람다 함수로 지정할 수 있어 매우 간결합니다.

- **Kotlin 코드**
  ```kotlin
  // availableLengths[k]가 true이면 길이 k를 사용할 수 있음을 의미합니다.
  // it은 인덱스를 의미하며, it > 0 조건에 따라 0번 인덱스는 false, 나머지는 true로 초기화됩니다.
  val availableLengths = BooleanArray(n + 1) { it > 0 }
  ```

- **Java 비교**
  ```java
  boolean[] availableLengths = new boolean[n + 1];
  for (int k = 1; k <= n; k++) {
      availableLengths[k] = true;
  }
  ```

### 2. `if-else` 표현식 (Expression)

Java의 삼항 연산자와 유사하게, 코틀린의 `if-else`는 값을 반환하는 표현식으로 사용될 수 있어 코드를 더 깔끔하게 만듭니다.

- **Kotlin 코드**
  ```kotlin
  // 인덱스 i-1에서 끝난 연산의 수
  val endedBefore = if (i > 0) endsCountAt[i - 1] else 0
  ```

- **Java 비교**
  ```java
  // 삼항 연산자 사용
  int endedBefore = (i > 0) ? endsCountAt[i - 1] : 0;

  // 일반적인 if-else 블록 사용
  int endedBefore;
  if (i > 0) {
      endedBefore = endsCountAt[i - 1];
  } else {
      endedBefore = 0;
  }
  ```

### 3. `for` 루프와 진행(Progression) 문법 (`downTo`)

코틀린은 `for` 루프에서 사용할 수 있는 다양한 진행 관련 문법을 제공하여 가독성을 높입니다.

- **Kotlin 코드**
  ```kotlin
  // 현재 위치 i에서 시작할 수 있는 가장 긴 길이부터 탐욕적으로 할당합니다.
  // (n - i)부터 1까지 1씩 감소하며 순회합니다.
  for (k in (n - i) downTo 1) {
      // ...
  }
  ```

- **Java 비교**
  ```java
  for (int k = n - i; k >= 1; k--) {
      // ...
  }
  ```
  코틀린의 `downTo`는 역순으로 반복하는 의도를 명확하게 보여줍니다. 참고로 `i in 0 until n`은 `0`부터 `n-1`까지 순회하는, 끝이 제외된 범위를 의미합니다.

### 4. 표준 입력을 처리하는 함수 체이닝

코틀린의 표준 라이브러리는 함수형 프로그래밍 스타일을 지원하여, 여러 함수를 연결(chaining)하여 데이터를 간결하게 처리할 수 있습니다.

- **Kotlin 코드**
  ```kotlin
  val p = readln().split(" ").map { it.toInt() }.toIntArray()
  ```
  위 코드는 한 줄로 다음 작업을 수행합니다.
  1.  `readln()`: 한 줄을 문자열로 읽습니다.
  2.  `split(" ")`: 공백을 기준으로 문자열을 분리하여 문자열 리스트를 만듭니다.
  3.  `map { it.toInt() }`: 리스트의 각 문자열(`it`)을 정수(`Int`)로 변환하여 새로운 리스트를 만듭니다.
  4.  `toIntArray()`: `List<Int>`를 원시 타입 배열인 `IntArray`로 변환합니다.

- **Java 비교**
  ```java
  // Java 8 스트림 API를 사용한 유사한 로직
  Scanner scanner = new Scanner(System.in);
  int[] p = Arrays.stream(scanner.nextLine().split(" "))
                  .mapToInt(Integer::parseInt)
                  .toArray();
  ```
  코틀린의 방식이 더 짧고 직관적인 경우가 많습니다.
