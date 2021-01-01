
data class Destruct<A, B, C, D, E>(val one: A, val two: B, val three: C, val four: D, val five: E)

fun <T> MutableList<T>.swap(i: Int, j: Int): MutableList<T> {
    with(this[i]) {
        this@swap[i] = this@swap[j]
        this@swap[j] = this
    }

    return this
}