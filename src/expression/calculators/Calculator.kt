package expression.calculators

interface Calculator<T> {
    fun add(x: T, y: T): T
    fun sub(x: T, y: T): T
    fun mul(x: T, y: T): T
    fun div(x: T, y: T): T
    fun neg(x: T): T
    fun parse(s: String): T
    fun mod(x: T, y: T): T
    fun abs(x: T): T
    fun square(x: T): T
}