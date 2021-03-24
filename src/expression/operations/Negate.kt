package expression.operations

import expression.GenericExpression
import expression.calculators.Calculator


class Negate(term: GenericExpression) : AbstractUnaryOperation(term, "-") {
    override fun <T> calculate(x: T, calculator: Calculator<T>): T {
        return calculator.neg(x)
    }
}
