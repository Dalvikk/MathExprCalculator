package expression

import expression.calculators.*
import expression.exceptions.ParseException
import expression.parser.ExpressionParser


val CALCULATOR_BY_MODE: Map<String, Calculator<*>> = mapOf(
    Pair("bi", BigIntCalculator()),
    Pair("i", CheckedIntCalculator()),
    Pair("ci", IntCalculator()),
    Pair("d", DoubleCalculator()),
    Pair("p", ModCalculator())
)

fun safeReadInt(): Int {
    var res: Int
    while (true) {
        val line = readLine()!!
        try {
            res = line.toInt()
            return res
        } catch (ignored: NumberFormatException) {
            println("$line isn't a Int. Please, retry:")
        }
    }
}

fun readCalculator(): Calculator<*> {
    val calculator: Calculator<*>
    var mode: String
    println("Enter mode: ")
    while (true) {
        mode = readLine()!!
        if (mode in CALCULATOR_BY_MODE) {
            calculator = CALCULATOR_BY_MODE[mode]!!
            if (calculator is ModCalculator) {
                println("Enter mod:")
                calculator.setMod(safeReadInt())
            }
            return calculator
        }
        println("Incorrect mode: $mode. Available mods: ${CALCULATOR_BY_MODE.keys}. Please, retry:")
    }
}

fun readVariablesValues(parser: ExpressionParser): Map<String, String> {
    val map = HashMap<String, String>()
    for (variable in parser.getVariables()) {
        println("Enter $variable value: ")
        map[variable] = readLine()!!
    }
    return map
}

fun main(args: Array<String>) {
    val debug = args.contains("-debug")
    val parser = ExpressionParser(readLine()!!)
    val result: GenericExpression
    try {
        result = parser.parse()
        if (debug) {
            println("DEBUG: $result")
        }
        val calculator: Calculator<*> = readCalculator()
        val map = readVariablesValues(parser)
        print(result.evaluate(map, calculator))
    } catch (e: ParseException) {
        print(e.message)
    } catch (e: ArithmeticException) {
        print(e.message)
    }
}
