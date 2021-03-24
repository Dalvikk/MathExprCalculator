package expression

import expression.calculators.IntCalculator
import expression.parser.ExpressionParser

fun main() {
    val parser = ExpressionParser()
    val result = parser.parse(readLine()!!)
    val x = readLine()!!.toInt()
    val y = readLine()!!.toInt()
    val z = readLine()!!.toInt()
    print(result.evaluate(x, y, z, IntCalculator()))
}
