package expression.exceptions

class DivisionByZeroException(x: Any, y: Any, expression: String, pos: Int) : ArithmeticException(
    MessageCreator.createHighlightMessage(
        "Division by zero. Left = $x, right = $y.",
        expression,
        pos
    )
)
