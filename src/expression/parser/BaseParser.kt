package expression.parser

abstract class BaseParser(private val source: CharSource) {
    private val end = '\u0000'
    private val buffer = ArrayDeque<Char>()
    protected var readCnt = 0

    protected val next: Char
        get() {
            if (buffer.isEmpty()) {
                readNextFromSource()
            }
            return buffer.first()
        }

    private fun readNextFromSource() {
        buffer.addLast(if (source.hasNext()) source.next() else end)
    }

    protected fun pop(): Char {
        readCnt++
        return buffer.removeFirst()
    }


    protected fun skipWhitespaces() {
        while (Character.isWhitespace(next)) {
            pop()
        }
    }

    protected fun test(e: Char): Boolean {
        if (next == e) {
            pop()
            return true
        }
        return false
    }

    protected fun test(expected: String): Boolean {
        while (buffer.size < expected.length) {
            readNextFromSource()
        }
        for ((idx, element) in expected.withIndex()) {
            if (element != buffer[idx]) {
                return false
            }
        }
        for (i in 1..expected.length) {
            pop()
        }
        return true
    }

    protected fun eof(): Boolean {
        return next == end
    }
}
