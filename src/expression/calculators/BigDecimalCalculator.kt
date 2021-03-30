package expression.calculators

import expression.exceptions.DivisionByZeroException
import expression.exceptions.MessageCreator
import expression.exceptions.ModByZeroException
import expression.operations.AbstractNAryOperation
import java.math.BigDecimal

class BigDecimalCalculator : Calculator<BigDecimal> {
    override fun add(x: BigDecimal, y: BigDecimal, wrapper: AbstractNAryOperation): BigDecimal {
        return x.add(y)
    }

    override fun sub(x: BigDecimal, y: BigDecimal, wrapper: AbstractNAryOperation): BigDecimal {
        return x.subtract(y)
    }

    override fun mul(x: BigDecimal, y: BigDecimal, wrapper: AbstractNAryOperation): BigDecimal {
        return x.multiply(y)
    }

    override fun div(x: BigDecimal, y: BigDecimal, wrapper: AbstractNAryOperation): BigDecimal {
        if (y == BigDecimal.ZERO) {
            throw DivisionByZeroException(x, y, wrapper.getExpression(), wrapper.getPos())
        }
        return x.div(y)
    }

    override fun mod(x: BigDecimal, y: BigDecimal, wrapper: AbstractNAryOperation): BigDecimal {
        if (y == BigDecimal.ZERO) {
            throw ModByZeroException(x, y, wrapper.getExpression(), wrapper.getPos())
        }
        if (y.compareTo(BigDecimal.ZERO) == -1) {
            throw ArithmeticException(
                MessageCreator.createHighlightMessage(
                    "Mod by negative number. 'mode' always returns a non-negative BigDecimal." +
                            "\nMaybe you want add 'remainder' operation?\n" +
                            "Left = $x, right = $y.", wrapper.getExpression(), wrapper.getPos()
                )
            )
        }
        // BigDecimal doesn't have mod function
        val result = x.remainder(y)
        return if (result.signum() >= 0) result else result.add(y)
    }

    override fun neg(x: BigDecimal, wrapper: AbstractNAryOperation): BigDecimal {
        return x.negate()
    }

    override fun parse(s: String, wrapper: AbstractNAryOperation): BigDecimal {
        return BigDecimal(s).setScale(50)
    }

    override fun abs(x: BigDecimal, wrapper: AbstractNAryOperation): BigDecimal {
        return x.abs()
    }

    override fun square(x: BigDecimal, wrapper: AbstractNAryOperation): BigDecimal {
        return x.multiply(x)
    }
}
