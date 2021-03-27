package expression.calculators

import expression.exceptions.DivisionByZeroException
import expression.exceptions.MessageCreator
import expression.exceptions.ModByZeroException
import expression.operations.AbstractBinaryOperation
import expression.operations.AbstractUnaryOperation
import expression.operations.Const
import java.math.BigInteger

class BigIntCalculator : Calculator<BigInteger> {
    override fun add(x: BigInteger, y: BigInteger, wrapper: AbstractBinaryOperation): BigInteger {
        return x.add(y)
    }

    override fun sub(x: BigInteger, y: BigInteger, wrapper: AbstractBinaryOperation): BigInteger {
        return x.subtract(y)
    }

    override fun mul(x: BigInteger, y: BigInteger, wrapper: AbstractBinaryOperation): BigInteger {
        return x.multiply(y)
    }

    override fun div(x: BigInteger, y: BigInteger, wrapper: AbstractBinaryOperation): BigInteger {
        if (y == BigInteger.ZERO) {
            throw DivisionByZeroException(x, y, wrapper.getExpression(), wrapper.getPos())
        }
        return x.divide(y)
    }

    override fun neg(x: BigInteger, wrapper: AbstractUnaryOperation): BigInteger {
        return x.negate()
    }

    override fun parse(s: String, wrapper: Const): BigInteger {
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

    override fun mod(x: BigInteger, y: BigInteger, wrapper: AbstractBinaryOperation): BigInteger {
        if (y == BigInteger.ZERO) {
            throw ModByZeroException(x, y, wrapper.getExpression(), wrapper.getPos())
        }
        if (y.compareTo(BigInteger.ZERO) == -1) {
            throw ArithmeticException(
                MessageCreator.createHighlightMessage(
                    "Mod by negative number. BigInteger's mode always returns a non-negative BigInteger." +
                            "Maybe you want add 'remainder' operation?" +
                            "Left = $x, right = $y.", wrapper.getExpression(), wrapper.getPos()
                )
            )
        }
        return x.mod(y)
    }

    override fun abs(x: BigInteger, wrapper: AbstractUnaryOperation): BigInteger {
        return x.abs()
    }

    override fun square(x: BigInteger, wrapper: AbstractUnaryOperation): BigInteger {
        return x.multiply(x)
    }
}
