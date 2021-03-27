package expression.operations

import expression.calculators.Calculator
import expression.parser.Connector

class Const(private val value: String, connector: Connector) : AbstractNAryOperation(connector) {
    override fun <T> evaluate(x: T, y: T, z: T, calculator: Calculator<T>): T {
        return calculator.parse(value, this)
    }

    override fun toString(): String {
        return value
    }
}
