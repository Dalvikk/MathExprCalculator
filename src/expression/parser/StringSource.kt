package expression.parser

class StringSource(private val data: String) : CharSource {
    private var pos = 0
    override fun hasNext(): Boolean {
        return pos < data.length
    }

    override fun next(): Char {
        return data[pos++]
    }
}