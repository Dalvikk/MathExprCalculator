package expression.operations


import expression.GenericExpression
import expression.calculators.Calculator


class Add(first: GenericExpression, second: GenericExpression) : AbstractBinaryOperation(first, second, "+") {
    override fun <T> calculate(x: T, y: T, calculator: Calculator<T>): T {
        return calculator.add(x, y)
    }
}