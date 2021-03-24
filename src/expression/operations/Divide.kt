package expression.operations

import expression.GenericExpression
import expression.calculators.Calculator

class Divide(first: GenericExpression, second: GenericExpression) : AbstractBinaryOperation(first, second, "/") {
    override fun <T> calculate(x: T, y: T, calculator: Calculator<T>): T {
        return calculator.div(x, y)
    }
}