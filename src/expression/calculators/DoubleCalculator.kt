package expression.calculators

import expression.exceptions.DivisionByZeroException
import expression.exceptions.MessageCreator
import expression.exceptions.ModByZeroException
import expression.operations.AbstractNAryOperation

class DoubleCalculator : Calculator<Double> {
    override fun add(x: Double, y: Double, wrapper: AbstractNAryOperation): Double {
        return x + y
    }

    override fun sub(x: Double, y: Double, wrapper: AbstractNAryOperation): Double {
        return x - y
    }

    override fun mul(x: Double, y: Double, wrapper: AbstractNAryOperation): Double {
        return x * y
    }

    override fun div(x: Double, y: Double, wrapper: AbstractNAryOperation): Double {
        if (y.equals(0.00)) {
            throw DivisionByZeroException(x, y, wrapper.getExpression(), wrapper.getPos())
        }
        return x / y
    }

    override fun neg(x: Double, wrapper: AbstractNAryOperation): Double {
        return -x
    }

    override fun parse(s: String, wrapper: AbstractNAryOperation): Double {
        try {
            return s.toDouble()
        } catch (ignored: NumberFormatException) {
            throw ArithmeticException(
                MessageCreator.createHighlightMessage(
                    "NumberFormatException while parsing: $s isn't a Double",
                    wrapper.getExpression(),
                    wrapper.getPos()
                )
            )
        }
    }

    override fun mod(x: Double, y: Double, wrapper: AbstractNAryOperation): Double {
        if (y.equals(0.00)) {
            throw ModByZeroException(x, y, wrapper.getExpression(), wrapper.getPos())
        }
        return x % y
    }

    override fun abs(x: Double, wrapper: AbstractNAryOperation): Double {
        return kotlin.math.abs(x)
    }

    override fun square(x: Double, wrapper: AbstractNAryOperation): Double {
        return x * x
    }
}
