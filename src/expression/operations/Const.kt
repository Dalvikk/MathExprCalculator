package expression.operations

import expression.GenericExpression
import expression.calculators.Calculator
import expression.exceptions.OverflowException

class Const(private val value: String) : GenericExpression {
    override fun <T> evaluate(x: T, y: T, z: T, calculator: Calculator<T>): T {
        return try {
            calculator.parse(value)
        } catch (ignored: NumberFormatException) {
            throw OverflowException(value)
        }
    }

    override fun toString(): String {
        return value
    }
}
