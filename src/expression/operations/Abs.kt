package expression.operations

import expression.GenericExpression
import expression.calculators.Calculator

class Abs(term: GenericExpression) : AbstractUnaryOperation(term, "abs") {
    override fun <T> calculate(x: T, calculator: Calculator<T>): T {
        return calculator.abs(x)
    }
}
