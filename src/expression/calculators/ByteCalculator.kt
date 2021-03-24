package expression.calculators

import expression.exceptions.DivisionByZeroException

class ByteCalculator : Calculator<Byte> {
    override fun add(x: Byte, y: Byte): Byte {
        return (x + y).toByte()
    }

    override fun sub(x: Byte, y: Byte): Byte {
        return (x - y).toByte()
    }

    override fun mul(x: Byte, y: Byte): Byte {
        return (x * y).toByte()
    }

    override fun div(x: Byte, y: Byte): Byte {
        if (y.toInt() == 0) {
            throw DivisionByZeroException("Division by zero")
        }
        return (x / y).toByte()
    }

    override fun neg(x: Byte): Byte {
        return (-x).toByte()
    }

    override fun parse(s: String): Byte {
        return s.toInt().toByte()
    }

    override fun mod(x: Byte, y: Byte): Byte {
        return (x % y).toByte()
    }

    override fun abs(x: Byte): Byte {
        return kotlin.math.abs(x.toInt()).toByte()
    }

    override fun square(x: Byte): Byte {
        return (x * x).toByte()
    }
}