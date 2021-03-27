package expression.exceptions

class MissingLeftBracketException(expression: String, pos: Int) : ArithmeticException(
    MessageCreator.createHighlightMessage(
        "Left bracket missing",
        expression,
        pos
    )
)
