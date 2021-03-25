package expression

import expression.calculators.IntCalculator
import expression.parser.ExpressionParser

fun main() {
    val parser = ExpressionParser(readLine()!!)
    val result = parser.parse()
    val x = readLine()!!.toInt()
    val y = readLine()!!.toInt()
    val z = readLine()!!.toInt()
    print(result.evaluate(x, y, z, IntCalculator()))
}
