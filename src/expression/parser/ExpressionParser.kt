package expression.parser

import expression.GenericExpression
import expression.exceptions.MessageCreator
import expression.exceptions.MissingLeftBracketException
import expression.exceptions.MissingRightBracketException
import expression.exceptions.ParseException
import expression.operations.*


class ExpressionParser : BaseParser() {
    private var expression: String? = null
    private fun init(expression: String?) {
        super.init(StringSource(expression!!))
        this.expression = expression
    }

    fun parse(expression: String): GenericExpression {
        init(expression)
        val result = parseExpression()
        skipWhitespaces()
        return if (!eof()) {
            throw ParseException(
                MessageCreator.createHighlightMessage(
                    "Unexpected symbol: $next", expression, readCnt + 1
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

    private fun nextBinaryTerm(priority: Int): GenericExpression {
        return if (priority == Operation.getPriority(Operation.CONST)) {
            parseUnary()
        } else {
            parseBinaryTerm(priority)
        }
    }

    private fun parseBinaryTerm(priority: Int): GenericExpression {
        var result = nextBinaryTerm(priority + 1)
        skipWhitespaces()
        while (true) {
            val operation = getNextOperation(priority) ?: return result
            val rightTerm = nextBinaryTerm(priority + 1)
            result = binaryOperation(operation, result, rightTerm)
            skipWhitespaces()
        }
    }

    private fun getNextOperation(priority: Int): Operation? {
        for (operation in Operation.OPERATIONS_BY_PRIORITIES[priority]) {
            val op: String? = Operation.STRING_BY_OPERATOR[operation]
            if (op != null && test(op)) {
                return operation
            }
        }
        if (priority == Operation.getPriority(Operation.CONST)) {
            if (isNextCharBetween('x', 'z')) {
                return Operation.VAR
            }
            if (isNextCharBetween('0', '9')) {
                return Operation.CONST
            }
        }
        return null
    }

    private fun binaryOperation(
        operation: Operation,
        left: GenericExpression,
        right: GenericExpression
    ): GenericExpression {
        return when (operation) {
            Operation.ADD -> Add(left, right)
            Operation.SUB -> Subtract(left, right)
            Operation.MUL -> Multiply(left, right)
            Operation.DIV -> Divide(left, right)
            Operation.MOD -> Mod(left, right)
            else -> throw AssertionError("Unknown operation:$operation")
        }
    }

    private fun parseUnary(): GenericExpression {
        skipWhitespaces()
        val operation =
            getNextOperation(Operation.getPriority(Operation.CONST))
        if (operation != null) {
            when (operation) {
                Operation.CONST -> return parseConst(true)
                Operation.VAR -> return parseVariable()
                Operation.LB -> {
                    val result = parseExpression()
                    skipWhitespaces()
                    if (!test(')')) {
                        throw MissingRightBracketException("Right bracket missing")
                    }
                    return result
                }
                Operation.RB -> throw MissingLeftBracketException("RB")
                Operation.NEGATE -> {
                    return if (isNextCharBetween('0', '9')) {
                        parseConst(false)
                    } else Negate(parseUnary())
                }
                Operation.SQUARE -> return Square(parseUnary())
                Operation.ABS -> return Abs(parseUnary())
            }
        }
        throw ParseException(
            MessageCreator.createHighlightMessage(
                "Unexpected symbol: $next",
                expression,
                readCnt + 1
            )
        )
    }

    private fun parseVariable(): Variable {
        val sb = StringBuilder()
        while (Character.isLetter(next)) {
            sb.append(next)
            pop()
        }
        return Variable(sb.toString())
    }

    private fun parseConst(isPositive: Boolean): Const {
        val sb = StringBuilder()
        if (!isPositive) {
            sb.append('-')
        }
        while (isNextCharBetween('0', '9')) {
            sb.append(next)
            pop()
        }
        return Const(sb.toString())
    }
}
