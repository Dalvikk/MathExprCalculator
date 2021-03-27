package expression.operations

import expression.calculators.Calculator
import expression.exceptions.ParseException
import expression.parser.Connector

class Variable(name: String, connector: Connector) : AbstractNAryOperation(connector) {
    private val name: String
    override fun <T> evaluate(x: T, y: T, z: T, calculator: Calculator<T>): T {
        when (name) {
            "x" -> return x
            "y" -> return y
            "z" -> return z
        }
        throw AssertionError("This code shouldn't have executed. An error has occurred, please contact the developer")
    }

    override fun toString(): String {
        return name
    }

    init {
        if (name != "x" && name != "y" && name != "z") {
            throw ParseException("Incorrect variable name: $name. Supports: x, y, z")
        }
        this.name = name
    }
}
