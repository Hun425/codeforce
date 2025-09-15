# 알고리즘 문제 풀이 프로젝트

이 프로젝트는 리트코드(LeetCode), 코드포스(Codeforces), 백준(BOJ) 등의 알고리즘 문제를 코틀린으로 풀고, 그 과정을 체계적으로 관리하기 위해 만들어졌습니다.

## 🚀 시작하기

이 프로젝트는 Gradle을 사용하여 빌드 및 의존성 관리를 합니다. 프로젝트를 클론하고 IDE에서 열면 바로 문제 풀이를 시작할 수 있습니다.

## 📂 프로젝트 구조

프로젝트는 문제 소스, 해설, 테스트 코드를 명확하게 분리하고, 날짜별로 문제를 그룹화하여 관리합니다.

```
/practice
├── build.gradle.kts            # 빌드 및 의존성 설정
├── src
│   ├── main
│   │   └── kotlin              # 소스 코드 루트
│   │       └── codeforces
│   │           └── 2025-09-16  # 날짜별 폴더
│   │               ├── a_cut_the_array # 문제 패키지
│   │               │   ├── README.md   # 한글 번역 문제 설명
│   │               │   ├── Solution.kt # 문제 풀이 코드
│   │               │   ├── answer.md   # 문제 풀이 해설
│   │               │   └── kotlin_syntax.md # 코틀린 문법 정리 (선택사항)
│   │               ├── b_maximum_cost_permutation
│   │               └── c_non_descending_arrays
│   └── test
│       └── kotlin              # 테스트 코드 루트
│           └── codeforces
│               └── 2025-09-16  # 날짜별 테스트 폴더
│                   ├── a_cut_the_array
│                   │   └── SolutionTest.kt
│                   ├── b_maximum_cost_permutation
│                   └── c_non_descending_arrays
└── README.md                   # 프로젝트 안내 파일
```

- **`src/main/kotlin`**: 문제의 실제 풀이 코드(`Solution.kt`), 한국어로 번역된 문제 설명(`README.md`), 그리고 상세한 풀이 해설(`answer.md`)이 위치합니다.
- **`src/test/kotlin`**: 작성한 코드를 검증하기 위한 단위 테스트 코드(`SolutionTest.kt`)가 위치합니다.
- **`build.gradle.kts`**: 코틀린 버전, JUnit 등 프로젝트에 필요한 라이브러리를 설정합니다.

## ✨ 새로운 문제 추가 워크플로우

새로운 코드포스 문제를 추가하는 과정은 다음과 같습니다. (예: "2025-09-17"에 "A. New Problem" 추가)

1.  **날짜별 디렉토리 생성** (최초 1회):
    ```bash
    mkdir -p src/main/kotlin/codeforces/2025-09-17
    mkdir -p src/test/kotlin/codeforces/2025-09-17
    ```

2.  **문제 패키지 생성**:
    `src/main/kotlin/codeforces/2025-09-17/` 경로 아래에 문제 이름을 기반으로 새로운 패키지를 생성합니다. (예: `a_new_problem`)

2.  **`README.md` 작성 (문제 번역)**:
    생성된 패키지 안에 `README.md` 파일을 만들고, 문제 내용을 한국어로 번역하여 작성합니다. 이때, 수학 공식은 가독성을 높이기 위해 **LaTeX 문법**을 사용합니다.

3.  **`Solution.kt` 작성 (풀이 코드)**:
    같은 패키지에 `Solution.kt` 파일을 생성하고, 문제 풀이 코드를 작성합니다. 코드포스 문제의 경우, 표준 입력을 처리할 수 있는 `main` 함수를 포함하는 것이 좋습니다.

    ```kotlin
    package codeforces.`2025-09-17`.a_new_problem

    class Solution {
        fun solve(n: Int, a: IntArray): Pair<Int, Int> {
            // 여기에 문제 풀이 코드를 작성합니다.
        }
    }
    ```

4.  **`answer.md` 작성 (풀이 해설)**:
    문제 풀이에 대한 상세한 해설을 `answer.md` 파일에 작성합니다. 문제 분석, 핵심 아이디어, 알고리즘 설계, 시간 복잡도 분석 등을 포함하여 다른 사람이 쉽게 이해할 수 있도록 설명합니다.

5.  **`SolutionTest.kt` 작성 (테스트 코드)**:
    `src/test/kotlin/codeforces/2025-09-17/` 경로 아래에 문제에 해당하는 테스트 패키지를 생성하고(예: `a_new_problem`), 그 안에 `SolutionTest.kt` 파일을 만듭니다. 문제에서 제공된 예제 입출력을 기반으로 테스트 케이스를 작성하여 풀이의 정확성을 검증합니다.

    ```kotlin
    package codeforces.`2025-09-17`.a_new_problem

    import org.junit.jupiter.api.Test
    // ...

    class SolutionTest {
        private val solution = Solution()

        @Test
        fun `테스트 케이스 1`() {
            // given
            val n = 6
            val a = intArrayOf(1, 2, 3, 4, 5, 6)

            // when
            val (l, r) = solution.solve(n, a)

            // then
            // 정답이 여러 개일 수 있으므로, 반환된 값이 유효한지 검증합니다.
            assertTrue(check(n, a, l, r))
        }
    }
    ```

6.  **테스트 실행**:
    IDE에서 `SolutionTest.kt` 파일을 열고 모든 테스트를 실행하여 풀이가 올바르게 작동하는지 최종 확인합니다.

7.  **kotlin_syntax.md 작성 (선택사항)**:
    코틀린 문법 학습이 필요한 경우, 해당 문제에서 사용된 코틀린 문법들을 Java와 비교하여 정리한 `kotlin_syntax.md` 파일을 작성합니다. 이는 Java 개발자가 코틀린을 학습할 때 유용한 참고 자료가 됩니다.

## 📝 코딩 컨벤션

이 프로젝트에서는 다음과 같은 코딩 컨벤션을 따릅니다:

### 주석 작성 규칙
- **모든 주석은 한글로 작성**합니다.
- 코드의 목적과 동작을 명확하게 설명합니다.
- 복잡한 알고리즘의 경우 단계별로 주석을 작성합니다.

```kotlin
// 좋은 예시
// 누락된 숫자들 찾기
for (i in p.indices) {
    if (p[i] > 0) {
        used[p[i]] = true
    }
}

// 나쁜 예시
// Find which numbers are missing
for (i in p.indices) {
    if (p[i] > 0) {
        used[p[i]] = true
    }
}
```

### 변수 및 함수 명명
- 변수명과 함수명은 영어로 작성하되, 의미가 명확해야 합니다.
- camelCase를 사용합니다.
- 불필요한 약어는 피합니다.

### 테스트 함수명
- 테스트 함수명은 한글로 작성하여 가독성을 높입니다.
- 백틱(\`)을 사용하여 공백을 포함한 함수명을 작성합니다.

```kotlin
@Test
fun `테스트 케이스 1 - 정상적인 경우`() {
    // 테스트 코드
}
```
