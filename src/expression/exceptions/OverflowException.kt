package expression.exceptions

class OverflowException(vararg a: Any) : ArithmeticException("Overflow. Term(s) = $a")
