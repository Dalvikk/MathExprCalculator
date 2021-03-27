package expression.exceptions

class ModByZeroException(x: Any, y: Any, expression: String, pos: Int) : ArithmeticException(
    MessageCreator.createHighlightMessage(
        "Mod by zero. Left = $x, right = $y.",
        expression,
        pos
    )
)
