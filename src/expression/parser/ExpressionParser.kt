package expression.parser

import expression.GenericExpression
import expression.exceptions.MessageCreator
import expression.exceptions.MissingLeftBracketException
import expression.exceptions.MissingRightBracketException
import expression.exceptions.ParseException
import expression.operations.*


class ExpressionParser(private val expression: String) : BaseParser(StringSource(expression)) {
    private val connector = Connector(expression)
    private val variables = LinkedHashSet<String>()

    fun parse(): GenericExpression {
        val result = parseExpression()
        skipWhitespaces()
        return if (!eof()) {
            throw ParseException(
                MessageCreator.createHighlightMessage(
                    "Binary operation expected", expression, readCnt + 1
                )
            )
        } else {
            result
        }
    }

    private fun parseExpression(): GenericExpression {
        skipWhitespaces()
        return parseBinaryTerm(0)
    }

    private fun nextTerm(priority: Int): GenericExpression {
        return if (priority == Operation.getPriority(Operation.CONST)) {
            parseUnary()
        } else {
            parseBinaryTerm(priority)
        }
    }


    private fun parseBinaryTerm(priority: Int): GenericExpression {
        var result = nextTerm(priority + 1)
        skipWhitespaces()
        while (true) {
            val operationPos = readCnt + 1
            val operation = getNextOperation(priority) ?: return result
            val rightTerm = nextTerm(priority + 1)
            result = binaryOperation(operation, result, rightTerm)
            connector.insert(result, operationPos)
            skipWhitespaces()
        }
    }

    private fun getNextOperation(priority: Int): Operation? {
        for (operation in Operation.operationsByPriority[priority]) {
            val op: String? = Operation.stringByOperator[operation]
            if (op != null && test(op)) {
                return operation
            }
        }
        if (priority == Operation.getPriority(Operation.CONST)) {
            if (next.isLetter()) {
                return Operation.VAR
            }
            if (next.isDigit()) {
                return Operation.CONST
            }
        }
        return null
    }

    private fun binaryOperation(
        operation: Operation,
        left: GenericExpression,
        right: GenericExpression,
    ): GenericExpression {
        return when (operation) {
            Operation.ADD -> Add(left, right, connector)
            Operation.SUB -> Subtract(left, right, connector)
            Operation.MUL -> Multiply(left, right, connector)
            Operation.DIV -> Divide(left, right, connector)
            Operation.MOD -> Mod(left, right, connector)
            else -> throw AssertionError("This code shouldn't have executed. An error has occurred, please contact the developer")
        }
    }

    private fun parseUnary(): GenericExpression {
        skipWhitespaces()
        val operationPos = readCnt + 1
        var ans: GenericExpression? = null
        val operation = getNextOperation(Operation.getPriority(Operation.CONST))
        if (operation != null) {
            ans = when (operation) {
                Operation.CONST -> parseConst()
                Operation.VAR -> parseVariable()
                Operation.LB -> {
                    val result = parseExpression()
                    skipWhitespaces()
                    if (!test(')')) {
                        throw MissingRightBracketException(expression, operationPos)
                    }
                    result
                }
                Operation.RB -> throw MissingLeftBracketException(expression, operationPos)
                Operation.NEGATE -> Negate(parseUnary(), connector)
                Operation.SQUARE -> Square(parseUnary(), connector)
                Operation.ABS -> Abs(parseUnary(), connector)
                else -> throw AssertionError("This code shouldn't have executed. An error has occurred, please contact the developer")
            }
        }
        if (ans == null) {
            throw ParseException(
                MessageCreator.createHighlightMessage(
                    "Const, variable, '(' or unary operation expected:",
                    expression,
                    readCnt + 1
                )
            )
        } else if (operation != Operation.LB) {
            connector.insert(ans, operationPos)
        }
        return ans
    }

    private fun parseVariable(): Variable {
        val sb = StringBuilder()
        while (Character.isLetter(next)) {
            sb.append(next)
            pop()
        }
        variables.add(sb.toString())
        return Variable(sb.toString(), connector)
    }

    private fun parseConst(): Const {
        val sb = StringBuilder()
        while (next.isDigit()) {
            sb.append(next)
            pop()
        }
        return Const(sb.toString(), connector)
    }

    fun getVariables(): Set<String> {
        return variables
    }
}
