package expression.parser

import java.util.*

abstract class BaseParser {
    private val buffer = ArrayDeque<Char>()
    private var source: CharSource? = null
    protected var readCnt = 0
        private set

    fun init(source: CharSource?) {
        this.source = source
        readCnt = 0
        buffer.clear()
        readNextFromSource()
    }

    protected val next: Char
        protected get() {
            if (buffer.isEmpty()) {
                readNextFromSource()
            }
            return buffer.peekFirst()
        }

    private fun readNextFromSource() {
        buffer.addLast(if (source!!.hasNext()) source!!.next() else END)
    }

    protected fun pop() {
        buffer.pollFirst()
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
        while (buffer.size <= expected.length) {
            readNextFromSource()
        }
        val it: Iterator<Char> = buffer.iterator()
        for (element in expected) {
            if (it.next() != element) {
                return false
            }
        }
        val lastChar = Character.isLetter(expected[expected.length - 1])
        val nextChar = Character.isLetter(it.next())
        if (!(lastChar && nextChar)) {
            for (i in expected.indices) {
                pop()
            }
            return true
        }
        return false
    }

    protected fun eof(): Boolean {
        return test(END)
    }

    protected fun isNextCharBetween(from: Char, to: Char): Boolean {
        return next in from..to
    }

    companion object {
        const val END = '\u0000'
    }
}