package expression.exceptions

object MessageCreator {
    fun createHighlightMessage(message: String, expression: String?, pos: Int): String {
        require(!(pos < 1 || pos > expression!!.length))
        val up = "=".repeat(expression!!.length)
        val down = "=".repeat(pos - 1) + "^" + "=".repeat(expression.length - pos)
        return message +
                System.lineSeparator() +
                up + System.lineSeparator() +
                expression + System.lineSeparator() +
                down
    }
}