package expression.calculators

import expression.exceptions.MessageCreator
import expression.exceptions.OverflowException
import expression.operations.AbstractNAryOperation

class CheckedIntCalculator : IntCalculator() {

    override fun add(x: Int, y: Int, wrapper: AbstractNAryOperation): Int {
        if ((x > 0 && y > 0 && Int.MAX_VALUE - y < x) || (x < 0 && y < 0 && Int.MIN_VALUE - y > x)) {
            throw createOverflowException(wrapper, x, y)
        }
        return super.add(x, y, wrapper)
    }

    override fun sub(x: Int, y: Int, wrapper: AbstractNAryOperation): Int {
        if ((x >= 0 && y < 0 && Int.MAX_VALUE + y < x) || (x < 0 && y > 0 && Int.MIN_VALUE + y > x)) {
            throw createOverflowException(wrapper, x, y)
        }
        return super.sub(x, y, wrapper)
    }

    override fun mul(x: Int, y: Int, wrapper: AbstractNAryOperation): Int {
        if ((x > 0 && y > 0 && Int.MAX_VALUE / x < y) ||
            (x < 0 && y < 0 && Int.MAX_VALUE / x > y) ||
            (x > 0 && y < 0 && Int.MIN_VALUE / x > y) ||
            (x < 0 && y > 0 && Int.MIN_VALUE / y > x)
        ) {
            throw createOverflowException(wrapper, x, y)
        }
        return super.mul(x, y, wrapper)
    }

    override fun div(x: Int, y: Int, wrapper: AbstractNAryOperation): Int {
        if (x == Int.MIN_VALUE && y == -1) {
            throw createOverflowException(wrapper, x, y)
        }
        return super.div(x, y, wrapper)
    }

    override fun neg(x: Int, wrapper: AbstractNAryOperation): Int {
        if (x == Int.MIN_VALUE) {
            throw createOverflowException(wrapper, x)
        }
        return super.neg(x, wrapper)
    }

    override fun abs(x: Int, wrapper: AbstractNAryOperation): Int {
        if (x == Int.MIN_VALUE) {
            throw createOverflowException(wrapper, x)
        }
        return super.abs(x, wrapper)
    }

    override fun square(x: Int, wrapper: AbstractNAryOperation): Int {
        if ((x > 0 && Int.MAX_VALUE / x < x) || (x < 0 && Int.MAX_VALUE / x > x)) {
            throw createOverflowException(wrapper, x)
        }
        return super.square(x, wrapper)
    }

    private fun createOverflowException(wrapper: AbstractNAryOperation, vararg x: Int) =
        OverflowException(
            MessageCreator.createHighlightMessage(
                "Overflow detected. Term(s) = ${x.contentToString()}.",
                wrapper.getExpression(),
                wrapper.getPos()
            )
        )
}
