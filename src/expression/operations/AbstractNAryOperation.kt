package expression.operations

import expression.GenericExpression
import expression.parser.Connector

abstract class AbstractNAryOperation(protected val connector: Connector) : GenericExpression {
    fun getPos(): Int {
        return connector.getPos(this)
            ?: throw AssertionError("This code shouldn't have executed. An error has occurred, please contact the developer")

    }

    fun getExpression(): String {
        return connector.getExpression()
    }
}
