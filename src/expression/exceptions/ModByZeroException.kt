package expression.exceptions

class ModByZeroException(x: Any, y: Any) : ArithmeticException("Division by zero. Left term = $x, right term = $y")
