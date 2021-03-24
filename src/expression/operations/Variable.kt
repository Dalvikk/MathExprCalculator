package expression.operations

import expression.GenericExpression
import expression.calculators.Calculator
import expression.exceptions.ParseException

class Variable(name: String) : GenericExpression {
    private val name: String
    override fun <T> evaluate(x: T, y: T, z: T, calculator: Calculator<T>): T {
        when (name) {
            "x" -> return x
            "y" -> return y
            "z" -> return z
        }
        throw AssertionError("Unexpected variable: $name")
    }

    init {
        if (name != "x" && name != "y" && name != "z") {
            throw ParseException("Incorrect variable name: $name. Supports: x, y, z")
        }
        this.name = name
    }
}
