package expression.operations

import expression.GenericExpression
import expression.calculators.Calculator
import expression.parser.Connector

abstract class AbstractBinaryOperation(
    private val first: GenericExpression,
    private val second: GenericExpression,
    private val operationToken: String,
    connector: Connector
) : AbstractNAryOperation(connector) {

    protected abstract fun <T> calculate(x: T, y: T, calculator: Calculator<T>): T

    override fun <T> evaluate(variables: Map<String, String>, calculator: Calculator<T>): T {
        return calculate(first.evaluate(variables, calculator), second.evaluate(variables, calculator), calculator)
    }

    override fun toString(): String {
        return "($first) $operationToken ($second)"
    }
}
