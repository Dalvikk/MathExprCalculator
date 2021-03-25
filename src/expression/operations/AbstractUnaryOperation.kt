package expression.operations

import expression.GenericExpression
import expression.calculators.Calculator

abstract class AbstractUnaryOperation(private val term: GenericExpression, private val operationToken: String) :
    GenericExpression {

    protected abstract fun <T> calculate(x: T, calculator: Calculator<T>): T

    override fun <T> evaluate(x: T, y: T, z: T, calculator: Calculator<T>): T {
        return calculate(term.evaluate(x, y, z, calculator), calculator)
    }

    override fun toString(): String {
        return "($operationToken$term)"
    }
}

