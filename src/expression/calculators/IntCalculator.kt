package expression.calculators

import expression.exceptions.DivisionByZeroException
import expression.exceptions.ModByZeroException

open class IntCalculator : Calculator<Int> {
    override fun add(x: Int, y: Int): Int {
        return x  + y 
    }

    override fun sub(x: Int, y: Int): Int {
        return x  - y 
    }

    override fun mul(x: Int, y: Int): Int {
        return x  * y 
    }

    override fun div(x: Int, y: Int): Int {
        if (y == 0) {
            throw DivisionByZeroException(x, y)
        }
        return x  / y 
    }

    override fun neg(x: Int): Int {
        return -x 
    }

    override fun parse(s: String): Int {
        return s.toInt()
    }

    override fun mod(x: Int, y: Int): Int {
        if (y == 0) {
            throw ModByZeroException(x, y)
        }
        return x  % y 
    }

    override fun abs(x: Int): Int {
        return kotlin.math.abs(x )
    }

    override fun square(x: Int): Int {
        return x  * x
    }
}
