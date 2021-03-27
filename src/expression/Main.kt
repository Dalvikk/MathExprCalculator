package expression

import expression.calculators.CheckedIntCalculator
import expression.exceptions.ParseException
import expression.parser.ExpressionParser

fun main() {
    val parser = ExpressionParser(readLine()!!)
    val result: GenericExpression
    try {
        result = parser.parse()
        val map = HashMap<String, String>()
        for (variable in parser.getVariables()) {
            println("Enter $variable value:")
            map[variable] = readLine()!!
        }
        print(result.evaluate(map, CheckedIntCalculator()))
    } catch (e: ParseException) {
        print(e.message)
    } catch (e: ArithmeticException) {
        print(e.message)
    }
}
