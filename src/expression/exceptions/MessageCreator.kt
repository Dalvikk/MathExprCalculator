package expression.exceptions

object MessageCreator {
    fun createHighlightMessage(message: String, expression: String, pos: Int): String {
        require(pos in 1..expression.length)
        val up = "=".repeat(expression.length)
        val down = "=".repeat(pos - 1) + "^" + "=".repeat(expression.length - pos)
        return """
            |$message
            |$up
            |$expression
            |$down
            |""".trimMargin()
    }
}
