package template.geometry

/**
 * ✅ 2D 기본 기하
 * - CCW(orient), 선분 교차, 볼록껍질(Andrew monotone chain)
 */
data class Pt(val x: Long, val y: Long) : Comparable<Pt> {
    override fun compareTo(other: Pt): Int = if (x != other.x) x.compareTo(other.x) else y.compareTo(other.y)
}
fun cross(a: Pt, b: Pt, c: Pt): Long = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x)
fun orient(a: Pt, b: Pt, c: Pt): Int = cross(a, b, c).compareTo(0)
fun onSegment(a: Pt, b: Pt, p: Pt): Boolean =
    minOf(a.x, b.x) <= p.x && p.x <= maxOf(a.x, b.x) && minOf(a.y, b.y) <= p.y && p.y <= maxOf(a.y, b.y) && cross(a, b, p) == 0L

fun segInter(a: Pt, b: Pt, c: Pt, d: Pt): Boolean {
    val o1 = orient(a, b, c); val o2 = orient(a, b, d)
    val o3 = orient(c, d, a); val o4 = orient(c, d, b)
    if (o1 == 0 && onSegment(a, b, c)) return true
    if (o2 == 0 && onSegment(a, b, d)) return true
    if (o3 == 0 && onSegment(c, d, a)) return true
    if (o4 == 0 && onSegment(c, d, b)) return true
    return o1 * o2 < 0 && o3 * o4 < 0
}

fun convexHull(a: ArrayList<Pt>): List<Pt> {
    a.sort()
    if (a.size <= 1) return a
    val lower = ArrayList<Pt>()
    for (p in a) {
        while (lower.size >= 2 && cross(lower[lower.size-2], lower[lower.size-1], p) <= 0) lower.removeAt(lower.size-1)
        lower.add(p)
    }
    val upper = ArrayList<Pt>()
    for (i in a.size - 1 downTo 0) {
        val p = a[i]
        while (upper.size >= 2 && cross(upper[upper.size-2], upper[upper.size-1], p) <= 0) upper.removeAt(upper.size-1)
        upper.add(p)
    }
    lower.removeAt(lower.size - 1); upper.removeAt(upper.size - 1)
    lower.addAll(upper)
    return lower
}
