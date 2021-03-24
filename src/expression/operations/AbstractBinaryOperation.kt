package expression.operations

import expression.GenericExpression
import expression.calculators.Calculator
import expression.exceptions.EvaluateException

abstract class AbstractBinaryOperation(
    private val first: GenericExpression,
    private val second: GenericExpression,
    private val operationToken: String
) : GenericExpression {
    @Throws(EvaluateException::class)
    protected abstract fun <T> calculate(x: T, y: T, calculator: Calculator<T>): T
    @Throws(EvaluateException::class)
    override fun <T> evaluate(x: T, y: T, z: T, calculator: Calculator<T>): T {
        return calculate(first.evaluate(x, y, z, calculator), second.evaluate(x, y, z, calculator), calculator)
    }

    override fun toString(): String {
        return "($first $operationToken $second)"
    }
}