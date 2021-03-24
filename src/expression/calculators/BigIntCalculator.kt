package expression.calculators

import expression.exceptions.DivisionByZeroException
import expression.exceptions.ModByZeroException
import java.math.BigInteger

class BigIntCalculator : Calculator<BigInteger> {
    override fun add(x: BigInteger, y: BigInteger): BigInteger {
        return x.add(y)
    }

    override fun sub(x: BigInteger, y: BigInteger): BigInteger {
        return x.subtract(y)
    }

    override fun mul(x: BigInteger, y: BigInteger): BigInteger {
        return x.multiply(y)
    }

    override fun div(x: BigInteger, y: BigInteger): BigInteger {
        if (y == BigInteger.ZERO) {
            throw DivisionByZeroException("Division by 0")
        }
        return x.divide(y)
    }

    override fun neg(x: BigInteger): BigInteger {
        return x.negate()
    }

    override fun parse(s: String): BigInteger {
        return BigInteger(s)
    }

    override fun mod(x: BigInteger, y: BigInteger): BigInteger {
        if (y == BigInteger.ZERO) {
            throw ModByZeroException("Mod by zero")
        }
        return x.mod(y)
    }

    override fun abs(x: BigInteger): BigInteger {
        return x.abs()
    }

    override fun square(x: BigInteger): BigInteger {
        return x.multiply(x)
    }
}