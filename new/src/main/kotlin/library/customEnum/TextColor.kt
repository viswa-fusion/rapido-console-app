package library.customEnum

enum class TextColor(private val code: String) {
    RED("\u001B[31m"),
    Green("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    RESET("\u001B[0m"),
    MAGENTA("\u001B[35m");
    override fun toString(): String {
        return this.code
    }
}