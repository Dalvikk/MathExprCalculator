package expression.operations


import expression.GenericExpression
import expression.calculators.Calculator

class Square(term: GenericExpression) : AbstractUnaryOperation(term, "square") {
    override fun <T> calculate(x: T, calculator: Calculator<T>): T {
        return calculator.square(x)
    }
}