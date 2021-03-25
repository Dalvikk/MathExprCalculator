package expression.operations

import java.util.*

enum class Operation {
    ADD, SUB, MUL, DIV, MOD, SQUARE, ABS, NEGATE, CONST, VAR, LB, RB;

    companion object {
        val operationsByPriority = listOf(
            listOf(ADD, SUB),
            listOf(MUL, DIV),
            listOf(MOD),
            listOf(NEGATE, SQUARE, ABS, CONST, VAR, LB, RB)
        )

        var stringByOperator = EnumMap<Operation, String>(Operation::class.java)
        private var priorityByOperation = EnumMap<Operation, Int>(Operation::class.java)

        init {
            stringByOperator.putAll(
                mapOf(
                    Pair(ADD, "+"),
                    Pair(SUB, "-"),
                    Pair(MUL, "*"),
                    Pair(DIV, "/"),
                    Pair(MOD, "mod"),
                    Pair(NEGATE, "-"),
                    Pair(SQUARE, "square"),
                    Pair(ABS, "abs"),
                    Pair(LB, "("),
                    Pair(RB, ")"))
            )
        }

        init {
            for (priority in operationsByPriority.indices) {
                for (operation in operationsByPriority[priority]) {
                    priorityByOperation[operation] = priority
                }
            }
        }

        fun getPriority(operation: Operation): Int {
            return priorityByOperation[operation]!!
        }
    }
}
