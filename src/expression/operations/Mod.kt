package expression.operations


import expression.GenericExpression
import expression.calculators.Calculator
import expression.parser.Connector

class Mod(first: GenericExpression, second: GenericExpression, connector: Connector) :
    AbstractBinaryOperation(first, second, "mod", connector) {
    override fun <T> calculate(x: T, y: T, calculator: Calculator<T>): T {
        return calculator.mod(x, y, this)
    }
}
