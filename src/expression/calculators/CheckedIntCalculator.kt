package expression.calculators

import expression.exceptions.OverflowException

class CheckedIntCalculator : IntCalculator() {

    override fun add(x: Int, y: Int): Int {
        if (x > 0 && y > 0 && Int.MAX_VALUE - y < x
            || x < 0 && y < 0 && Int.MIN_VALUE - y > x
        ) {
            throw OverflowException(x, y)
        }
        return super.add(x, y)
    }

    override fun sub(x: Int, y: Int): Int {
        if (x >= 0 && y < 0 && Int.MAX_VALUE + y < x ||
            x < 0 && y > 0 && Int.MIN_VALUE + y > x
        ) {
            throw OverflowException(x, y)
        }
        return super.sub(x, y)
    }

    override fun mul(x: Int, y: Int): Int {
        if (x > 0 && y > 0 && Int.MAX_VALUE / x < y ||
            x < 0 && y < 0 && Int.MAX_VALUE / x > y ||
            x > 0 && y < 0 && Int.MIN_VALUE / x > y ||
            x < 0 && y > 0 && Int.MIN_VALUE / y > x
        ) {
            throw OverflowException(x, y)
        }
        return super.mul(x, y)
    }

    override fun div(x: Int, y: Int): Int {
        if (x == Int.MIN_VALUE && y == -1) {
            throw OverflowException(x, y)
        }
        return super.div(x, y)
    }

    override fun neg(x: Int): Int {
        if (x == Int.MIN_VALUE) {
            throw OverflowException(x)
        }
        return super.neg(x)
    }

    override fun abs(x: Int): Int {
        if (x == Int.MIN_VALUE) {
            throw OverflowException(x)
        }
        return super.abs(x)
    }

    override fun square(x: Int): Int {
        if (x > 0 && Int.MAX_VALUE / x < x ||
            x < 0 && Int.MAX_VALUE / x > x
        ) {
            throw OverflowException(x)
        }
        return super.square(x)
    }
}
