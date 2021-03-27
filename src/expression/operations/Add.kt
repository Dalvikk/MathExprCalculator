package expression.operations


import expression.GenericExpression
import expression.calculators.Calculator
import expression.parser.Connector


class Add(first: GenericExpression, second: GenericExpression, connector: Connector) :
    AbstractBinaryOperation(first, second, "+", connector) {
    override fun <T> calculate(x: T, y: T, calculator: Calculator<T>): T {
        return calculator.add(x, y, this)
    }
}
