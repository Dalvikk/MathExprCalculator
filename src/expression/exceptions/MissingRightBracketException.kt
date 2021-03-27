package expression.exceptions

class MissingRightBracketException(expression: String, pos: Int) : ArithmeticException(
    MessageCreator.createHighlightMessage(
        "Right bracket missing",
        expression,
        pos
    )
)
