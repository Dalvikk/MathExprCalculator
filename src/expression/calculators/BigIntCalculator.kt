package expression.calculators

import expression.exceptions.DivisionByZeroException
import expression.exceptions.MessageCreator
import expression.exceptions.ModByZeroException
import expression.operations.AbstractNAryOperation
import java.math.BigInteger

class BigIntCalculator : Calculator<BigInteger> {
    override fun add(x: BigInteger, y: BigInteger, wrapper: AbstractNAryOperation): BigInteger {
        return x.add(y)
    }

    override fun sub(x: BigInteger, y: BigInteger, wrapper: AbstractNAryOperation): BigInteger {
        return x.subtract(y)
    }

    override fun mul(x: BigInteger, y: BigInteger, wrapper: AbstractNAryOperation): BigInteger {
        return x.multiply(y)
    }

    override fun div(x: BigInteger, y: BigInteger, wrapper: AbstractNAryOperation): BigInteger {
        if (y == BigInteger.ZERO) {
            throw DivisionByZeroException(x, y, wrapper.getExpression(), wrapper.getPos())
        }
        return x.divide(y)
    }

    override fun neg(x: BigInteger, wrapper: AbstractNAryOperation): BigInteger {
        return x.negate()
    }

    override fun parse(s: String, wrapper: AbstractNAryOperation): BigInteger {
        try {
            return BigInteger(s)
        } catch (ignored: NumberFormatException) {
            throw ArithmeticException(
                MessageCreator.createHighlightMessage(
                    "NumberFormatException while parsing: $s isn't a BigInteger",
                    wrapper.getExpression(),
                    wrapper.getPos()
                )
            )
        }
    }

    override fun mod(x: BigInteger, y: BigInteger, wrapper: AbstractNAryOperation): BigInteger {
        if (y == BigInteger.ZERO) {
            throw ModByZeroException(x, y, wrapper.getExpression(), wrapper.getPos())
        }
        if (y.compareTo(BigInteger.ZERO) == -1) {
            throw ArithmeticException(
                MessageCreator.createHighlightMessage(
                    "Mod by negative number. BigInteger's mode always returns a non-negative BigInteger." +
                            "\nMaybe you want add 'remainder' operation?\n" +
                            "Left = $x, right = $y.", wrapper.getExpression(), wrapper.getPos()
                )
            )
        }
        return x.mod(y)
    }

    override fun abs(x: BigInteger, wrapper: AbstractNAryOperation): BigInteger {
        return x.abs()
    }

    override fun square(x: BigInteger, wrapper: AbstractNAryOperation): BigInteger {
        return x.multiply(x)
    }
}
