package expression.calculators

class DoubleCalculator : Calculator<Double> {
    override fun add(x: Double, y: Double): Double {
        return x + y
    }

    override fun sub(x: Double, y: Double): Double {
        return x - y
    }

    override fun mul(x: Double, y: Double): Double {
        return x * y
    }

    override fun div(x: Double, y: Double): Double {
        return x / y
    }

    override fun neg(x: Double): Double {
        return -x
    }

    override fun parse(s: String): Double {
        return s.toDouble()
    }

    override fun mod(x: Double, y: Double): Double {
        return x % y
    }

    override fun abs(x: Double): Double {
        return kotlin.math.abs(x)
    }

    override fun square(x: Double): Double {
        return x * x
    }
}