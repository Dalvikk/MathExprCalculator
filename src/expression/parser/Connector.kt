package expression.parser

import expression.GenericExpression

// This class -- the bridge between Lexeme and expression
class Connector(private val expression: String) {
    // Pos in expression string by operation.
    // Needed to throw an exception with a nice message and a highlighted position
    private val pos = HashMap<GenericExpression, Int>()

    fun insert(op: GenericExpression, _pos: Int) {
        pos[op] = _pos
    }

    fun getPos(op: GenericExpression): Int? {
        return pos[op]
    }

    fun getExpression(): String {
        return expression
    }
}
