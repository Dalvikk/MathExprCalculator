package expression.exceptions

class DivisionByZeroException(x: Any, y: Any) : EvaluateException("Division by zero. Left term = $x, right term = $y")
