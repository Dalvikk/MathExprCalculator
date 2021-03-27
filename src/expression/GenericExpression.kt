package expression

import expression.calculators.Calculator

interface GenericExpression {
    fun <T> evaluate(x: T, y: T, z: T, calculator: Calculator<T>): T
}
