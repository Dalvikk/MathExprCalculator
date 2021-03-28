package expression.operations

import expression.calculators.Calculator
import expression.exceptions.MessageCreator
import expression.parser.Connector

class Variable(private val name: String, connector: Connector) : AbstractNAryOperation(connector) {
    override fun <T> evaluate(variables: Map<String, String>, calculator: Calculator<T>): T {
        val res = variables[name]
        if (res != null) {
            return calculator.parse(res, this)
        }
        throw RuntimeException(
            MessageCreator.createHighlightMessage(
                "Variable value not found: $name",
                getExpression(),
                getPos()
            )
        )
    }

    override fun toString(): String {
        return name
    }
}
