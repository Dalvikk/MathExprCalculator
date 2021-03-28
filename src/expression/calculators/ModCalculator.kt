package expression.calculators

import expression.exceptions.DivisionByZeroException
import expression.operations.AbstractNAryOperation

class ModCalculator : IntCalculator() {
    private var mod: Int = 1
    private var inverses = IntArray(mod)

    fun setMod(_mod: Int) {
        mod = _mod
        inverses = IntArray(mod)
        inverses[1] = 1
        for (i in 2 until mod) inverses[i] = (mod - mod / i * inverses[mod % i] % mod) % mod
    }

    private fun getByMod(n: Int): Int {
        return if (n >= 0) n % mod else mod + n % mod
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

