# 알고리즘 문제 풀이 프로젝트

이 프로젝트는 리트코드(LeetCode), 코드포스, 백준 알고리즘 문제를 코틀린으로 풀고 관리하기 위해 만들어졌습니다.

## 🚀 시작하기

이 프로젝트는 Gradle을 사용하여 빌드 및 의존성 관리를 합니다. 프로젝트를 클론하고 IDE에서 열면 바로 문제 풀이를 시작할 수 있습니다.

## 📂 프로젝트 구조

프로젝트는 소스 코드와 테스트 코드를 명확하게 분리하고, 각 문제를 패키지로 나누어 관리합니다.

```
/practice
├── build.gradle                # 빌드 및 의존성 설정
├── src
│   ├── main
│   │   └── kotlin              # 소스 코드 루트
│   │       ├── leetcode
│   │       │   └── pXXXX       # 문제 XXXX번 풀이 패키지
│   │       │       ├── Solution.kt
│   │       │       └── README.md   # 한글 문제 설명
│   │       └── codeforces
│   │           └── cXXXX       # 대회 XXXX번 풀이 패키지
│   │               └── pA      # 문제 A번 풀이 패키지
│   │                   ├── Solution.kt
│   │                   └── README.md
│   └── test
│       └── kotlin              # 테스트 코드 루트
│           ├── leetcode
│           │   └── pXXXX       # 문제 XXXX번 테스트 패키지
│           │       └── SolutionTest.kt
│           └── codeforces
│               └── cXXXX
│                   └── pA
│                       └── SolutionTest.kt
└── README.md                   # 프로젝트 안내 파일
```

- **`src/main/kotlin`**: 문제에 대한 실제 풀이 코드가 위치합니다. 각 문제 폴더에는 한글로 번역된 문제 설명이 담긴 `README.md` 파일이 포함됩니다.
- **`src/test/kotlin`**: 작성한 코드를 검증하기 위한 테스트 코드가 위치합니다.
- **`build.gradle`**: 코틀린 버전, JUnit 등 프로젝트에 필요한 라이브러리를 설정합니다.

## ✨ 새로운 문제 추가하는 방법

새로운 리트코드 문제를 추가하는 과정은 다음과 같습니다. (예: 1234번 문제)

1.  **문제 설명 파일 생성**:
    `src/main/kotlin/leetcode/p1234/` 디렉터리를 생성하고, 그 안에 `README.md` 파일을 만들어 한글로 번역된 문제 설명을 작성합니다.

2.  **소스 파일 생성**:
    같은 디렉터리에 `Solution.kt` 파일을 만듭니다. 파일 상단에는 패키지 선언을 추가해야 합니다.

    ``` kotlin
    package leetcode.p1234

    class Solution {
        // 여기에 문제 풀이 코드를 작성합니다.
    }
    ```

3.  **테스트 파일 생성**:
    `src/test/kotlin/leetcode/p1234/` 디렉터리를 생성하고, 그 안에 `SolutionTest.kt` 파일을 만듭니다. 예제 입출력을 기반으로 테스트 케이스를 작성합니다.

    ``` kotlin
    package leetcode.p1234

    import org.junit.jupiter.api.Assertions.assertEquals
    import org.junit.jupiter.api.Test

    class SolutionTest {
        private val solution = Solution()

        @Test
        fun `테스트 케이스 1`() {
            // given
            val input = ""
            val expected = 0

            // when
            val result = solution.solve(input)

            // then
            assertEquals(expected, result)
        }
    }
    ```

4.  **테스트 실행**:
    IDE에서 `SolutionTest.kt` 파일을 열고 테스트를 실행하여 풀이가 올바르게 작동하는지 확인합니다.
