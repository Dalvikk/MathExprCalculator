package expression.calculators

import expression.exceptions.DivisionByZeroException
import expression.exceptions.OverflowException

class CheckedIntCalculator : IntCalculator() {
    @Throws(OverflowException::class)
    override fun add(x: Int, y: Int): Int {
        if (x > 0 && y > 0 && Int.MAX_VALUE - y < x
            || x < 0 && y < 0 && Int.MIN_VALUE - y > x
        ) {
            throw OverflowException("Overflow")
        }
        return super.add(x, y)
    }

    @Throws(OverflowException::class)
    override fun sub(x: Int, y: Int): Int {
        if (x >= 0 && y < 0 && Int.MAX_VALUE + y < x ||
            x < 0 && y > 0 && Int.MIN_VALUE + y > x
        ) {
            throw OverflowException("Overflow")
        }
        return super.sub(x, y)
    }

    @Throws(OverflowException::class)
    override fun mul(x: Int, y: Int): Int {
        if (x > 0 && y > 0 && Int.MAX_VALUE / x < y ||
            x < 0 && y < 0 && Int.MAX_VALUE / x > y ||
            x > 0 && y < 0 && Int.MIN_VALUE / x > y ||
            x < 0 && y > 0 && Int.MIN_VALUE / y > x
        ) {
            throw OverflowException("Overflow")
        }
        return super.mul(x, y)
    }

    @Throws(OverflowException::class, DivisionByZeroException::class)
    override fun div(x: Int, y: Int): Int {
        if (x == Int.MIN_VALUE && y == -1) {
            throw OverflowException("Overflow")
        }
        return super.div(x, y)
    }

    @Throws(OverflowException::class)
    override fun neg(x: Int): Int {
        if (x == Int.MIN_VALUE) {
            throw OverflowException("Overflow")
        }
        return super.neg(x)
    }

    @Throws(OverflowException::class)
    override fun abs(x: Int): Int {
        if (x == Int.MIN_VALUE) {
            throw OverflowException("Overflow")
        }
        return super.abs(x)
    }

    override fun square(x: Int): Int {
        if (x > 0 && Int.MAX_VALUE / x < x ||
            x < 0 && Int.MAX_VALUE / x > x
        ) {
            throw OverflowException("Overflow")
        }
        return super.square(x)
    }
}
