package expression.operations

import expression.GenericExpression
import expression.calculators.Calculator
import expression.parser.Connector


class Negate(term: GenericExpression, connector: Connector) : AbstractUnaryOperation(term, "-", connector) {
    override fun <T> calculate(x: T, calculator: Calculator<T>): T {
        return calculator.neg(x, this)
    }
}
