package expression.exceptions

class DivisionByZeroException(x: Any, y: Any) : ArithmeticException("Division by zero. Left term = $x, right term = $y")
