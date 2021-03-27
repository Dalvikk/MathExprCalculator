package expression.exceptions

object MessageCreator {
    fun createHighlightMessage(message: String, expression: String, pos: Int): String {
        require(pos in 1..expression.length + 1)
        val up = "=".repeat(expression.length)
        val down = "=".repeat(pos - 1) + "^" +
                if (pos <= expression.length) "=".repeat(expression.length - pos) else ""
        return """
            |$message
            |$up
            |$expression
            |$down
            |""".trimMargin()
    }
}
