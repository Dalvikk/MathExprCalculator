package expression.calculators

import expression.exceptions.DivisionByZeroException
import expression.exceptions.MessageCreator
import expression.exceptions.ModByZeroException
import expression.operations.AbstractNAryOperation

open class IntCalculator : Calculator<Int> {
    override fun add(x: Int, y: Int, wrapper: AbstractNAryOperation): Int {
        return x + y
    }

    override fun sub(x: Int, y: Int, wrapper: AbstractNAryOperation): Int {
        return x - y
    }

    override fun mul(x: Int, y: Int, wrapper: AbstractNAryOperation): Int {
        return x * y
    }

    override fun div(x: Int, y: Int, wrapper: AbstractNAryOperation): Int {
        if (y == 0) {
            throw DivisionByZeroException(x, y, wrapper.getExpression(), wrapper.getPos())
        }
        return x / y
    }

    override fun neg(x: Int, wrapper: AbstractNAryOperation): Int {
        return -x
    }

    override fun parse(s: String, wrapper: AbstractNAryOperation): Int {
        try {
            return s.toInt()
        } catch (ignored: NumberFormatException) {
            throw ArithmeticException(
                MessageCreator.createHighlightMessage(
                    "NumberFormatException while parsing: $s isn't a Int",
                    wrapper.getExpression(),
                    wrapper.getPos()
                )
            )
        }
    }

    override fun mod(x: Int, y: Int, wrapper: AbstractNAryOperation): Int {
        if (y == 0) {
            throw ModByZeroException(x, y, wrapper.getExpression(), wrapper.getPos())
        }
        return x % y
    }

    override fun abs(x: Int, wrapper: AbstractNAryOperation): Int {
        return kotlin.math.abs(x)
    }

    override fun square(x: Int, wrapper: AbstractNAryOperation): Int {
        return x * x
    }
}
