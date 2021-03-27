package expression

import expression.calculators.CheckedIntCalculator
import expression.exceptions.ParseException
import expression.parser.ExpressionParser

fun main() {
    val parser = ExpressionParser(readLine()!!)
    val result: GenericExpression
    try {
        result = parser.parse()
        val x = readLine()!!.toInt()
        val y = readLine()!!.toInt()
        val z = readLine()!!.toInt()
        print(result.evaluate(x, y, z, CheckedIntCalculator()))
    } catch (e: ParseException) {
        print(e.message)
    } catch (e: ArithmeticException) {
        print(e.message)
    }
}
