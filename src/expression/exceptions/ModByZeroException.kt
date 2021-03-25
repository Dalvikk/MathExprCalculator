package expression.exceptions

class ModByZeroException(x: Any, y: Any) : EvaluateException("Division by zero. Left term = $x, right term = $y")
