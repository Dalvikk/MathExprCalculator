package expression.operations

import expression.GenericExpression
import expression.calculators.Calculator
import expression.parser.Connector

class Abs(term: GenericExpression, connector: Connector) : AbstractUnaryOperation(term, "abs", connector) {
    override fun <T> calculate(x: T, calculator: Calculator<T>): T {
        return calculator.abs(x, this)
    }
}
