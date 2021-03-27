package expression.operations

import expression.GenericExpression
import expression.calculators.Calculator
import expression.parser.Connector

abstract class AbstractUnaryOperation(
    private val term: GenericExpression,
    private val operationToken: String,
    connector: Connector
) : AbstractNAryOperation(connector) {

    protected abstract fun <T> calculate(x: T, calculator: Calculator<T>): T

    override fun <T> evaluate(variables: Map<String, String>, calculator: Calculator<T>): T {
        return calculate(term.evaluate(variables, calculator), calculator)
    }

    override fun toString(): String {
        return "$operationToken ($term)"
    }
}

