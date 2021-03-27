package expression.calculators

import expression.exceptions.DivisionByZeroException
import expression.operations.AbstractNAryOperation

class ModCalculator(private val MOD: Int) : IntCalculator() {
    private val inverses = IntArray(MOD)

    init {
        inverses[1] = 1
        for (i in 2 until MOD) inverses[i] = (MOD - MOD / i * inverses[MOD % i] % MOD) % MOD
    }

    private fun getByMod(n: Int): Int {
        return if (n >= 0) n % MOD else MOD + n % MOD
    }

    override fun add(x: Int, y: Int, wrapper: AbstractNAryOperation): Int {
        return getByMod(super.add(x, y, wrapper))
    }

    override fun sub(x: Int, y: Int, wrapper: AbstractNAryOperation): Int {
        return getByMod(super.sub(x, y, wrapper))
    }

    override fun mul(x: Int, y: Int, wrapper: AbstractNAryOperation): Int {
        return getByMod(super.mul(x, y, wrapper))
    }

    override fun div(x: Int, y: Int, wrapper: AbstractNAryOperation): Int {
        if (y == 0) {
            throw DivisionByZeroException(x, y, wrapper.getExpression(), wrapper.getPos())
        }
        return getByMod(mul(x, inverses[y], wrapper))
    }

    override fun parse(s: String, wrapper: AbstractNAryOperation): Int {
        return getByMod(super.parse(s, wrapper))
    }

    override fun mod(x: Int, y: Int, wrapper: AbstractNAryOperation): Int {
        return getByMod(super.mod(x, y, wrapper))
    }

    override fun abs(x: Int, wrapper: AbstractNAryOperation): Int {
        return getByMod(super.abs(x, wrapper))
    }

    override fun square(x: Int, wrapper: AbstractNAryOperation): Int {
        return getByMod(super.square(x, wrapper))
    }
}

