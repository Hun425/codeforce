package template.common

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.StringTokenizer

/**
 * ✅ 목적
 * - Kotlin에서 표준 입력/출력을 빠르게 처리하기 위한 보일러플레이트(템플릿).
 *
 * 📌 핵심 개념
 * - StringTokenizer를 사용해 공백 단위 토큰 분리 (자바와 동일한 방식)
 * - nextInt/nextLong 등 타입별 파서 제공
 * - PrintWriter로 버퍼링된 출력 제공 → 마지막에 flush() 필수
 *
 * 🧠 자주 하는 실수
 * - 입력이 여러 줄일 때 nextLine()과 next()/StringTokenizer 혼용 시 공백/개행 처리 주의
 * - 출력 누락: 프로그램 종료 전 out.flush() 호출
 *
 * ⏱️ 복잡도
 * - I/O 자체는 O(입력길이)이며, 토큰화/파싱은 실전에서 가장 안정적인 편
 */
class FastScanner {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private var st: StringTokenizer? = null

    /** 다음 토큰(공백으로 구분된 문자열)을 반환합니다. */
    fun next(): String {
        // st가 아직 없거나, 현재 라인의 토큰을 모두 사용했으면 다음 줄을 읽어서 새 StringTokenizer 생성
        while (st == null || !st!!.hasMoreElements()) {
            st = StringTokenizer(br.readLine())
        }
        return st!!.nextToken()
    }

    /** 정수/실수/문자열 파서 */
    fun nextInt(): Int = next().toInt()
    fun nextLong(): Long = next().toLong()
    fun nextDouble(): Double = next().toDouble()

    /** 개행 기준으로 한 줄 전체를 그대로 가져옵니다. */
    fun nextLine(): String = br.readLine()

    /** 길이가 n인 IntArray/LongArray를 한 번에 읽습니다. */
    fun nextIntArray(n: Int): IntArray = IntArray(n) { nextInt() }
    fun nextLongArray(n: Int): LongArray = LongArray(n) { nextLong() }
}

/**
 * PrintWriter 기반 빠른 출력.
 * - println/print로 출력하고, 마지막에 반드시 flush() 호출!
 */
class FastOut {
    private val pw = PrintWriter(System.out)
    fun println(x: Any?) = pw.println(x)
    fun print(x: Any?) = pw.print(x)
    fun flush() = pw.flush()
}
