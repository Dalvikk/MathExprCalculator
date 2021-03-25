package expression.calculators

import expression.exceptions.DivisionByZeroException

class ModCalculator : IntCalculator() {
    private val inverses = IntArray(MOD)

    override fun add(x: Int, y: Int): Int {
        return getByMod(super.add(x, y))
    }

    override fun sub(x: Int, y: Int): Int {
        return getByMod(super.sub(x, y))
    }

    override fun mul(x: Int, y: Int): Int {
        return getByMod(super.mul(x, y))
    }

    override fun div(x: Int, y: Int): Int {
        if (y == 0) {
            throw DivisionByZeroException(x, y)
        }
        return getByMod(mul(x, inverses[y ]))
    }

    override fun parse(s: String): Int {
        return getByMod(super.parse(s))
    }

    override fun mod(x: Int, y: Int): Int {
        return getByMod(super.mod(x, y))
    }

    override fun abs(x: Int): Int {
        return getByMod(super.abs(x))
    }

    override fun square(x: Int): Int {
        return getByMod(super.square(x))
    }

    companion object {
        private const val MOD = 1009
        private fun getByMod(n: Int): Int {
            return if (n  >= 0) n % MOD else MOD + n % MOD
        }
    }

    init {
        inverses[1] = 1
        for (i in 2 until MOD) inverses[i] = (MOD - MOD / i * inverses[MOD % i] % MOD) % MOD
    }
}

