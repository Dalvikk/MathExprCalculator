package expression.operations


import expression.GenericExpression
import expression.calculators.Calculator
import expression.parser.Connector

class Square(term: GenericExpression, connector: Connector) : AbstractUnaryOperation(term, "square", connector) {
    override fun <T> calculate(x: T, calculator: Calculator<T>): T {
        return calculator.square(x, this)
    }
}
