package expression.parser

interface CharSource {
    fun hasNext(): Boolean
    fun next(): Char
}
