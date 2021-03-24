package expression

import expression.calculators.Calculator
import expression.exceptions.EvaluateException

interface GenericExpression {
    @Throws(EvaluateException::class)
    fun <T> evaluate(x: T, y: T, z: T, calculator: Calculator<T>): T
}