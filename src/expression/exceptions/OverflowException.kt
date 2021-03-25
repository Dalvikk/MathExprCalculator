package expression.exceptions

class OverflowException(vararg a: Any) : EvaluateException("Overflow. Term(s) = $a")
