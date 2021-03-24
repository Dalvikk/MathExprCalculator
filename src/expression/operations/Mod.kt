package expression.operations


import expression.GenericExpression
import expression.calculators.Calculator

class Mod(first: GenericExpression, second: GenericExpression) : AbstractBinaryOperation(first, second, "mod") {
    override fun <T> calculate(x: T, y: T, calculator: Calculator<T>): T {
        return calculator.mod(x, y)
    }
}