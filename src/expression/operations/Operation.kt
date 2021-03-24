package expression.operations

import java.util.*

enum class Operation {
    ADD, SUB, MUL, DIV, MOD, SQUARE, ABS, NEGATE, CONST, VAR, LB, RB;

    companion object {
        val OPERATIONS_BY_PRIORITIES = listOf(
            listOf(ADD, SUB),
            listOf(MUL, DIV),
            listOf(MOD),
            listOf(NEGATE, SQUARE, ABS, CONST, VAR, LB, RB)
        )
        var STRING_BY_OPERATOR = mapOf(
            Pair(ADD, "+"),
            Pair(SUB, "-"),
            Pair(MUL, "*"),
            Pair(DIV, "/"),
            Pair(MOD, "mod"),
            Pair(NEGATE, "-"),
            Pair(SQUARE, "square"),
            Pair(ABS, "abs"),
            Pair(LB, "("),
            Pair(RB, ")")
        )

        private
        var PRIORITY_BY_OPERATION: EnumMap<Operation, Int> = init()

        private fun init(): EnumMap<Operation, Int> {
            val map = EnumMap<Operation, Int>(Operation::class.java)
            for (priority in OPERATIONS_BY_PRIORITIES.indices) {
                for (operation in OPERATIONS_BY_PRIORITIES[priority]) {
                    map[operation] = priority
                }
            }
            return map
        }

        fun getPriority(operation: Operation): Int {
            val a = PRIORITY_BY_OPERATION[operation]
            return a!!
        }
    }
}