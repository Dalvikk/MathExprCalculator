package expression

import expression.calculators.Calculator

interface GenericExpression {
    fun <T> evaluate(variables: Map<String, String>, calculator: Calculator<T>): T
}
