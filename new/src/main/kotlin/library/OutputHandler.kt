package library

import library.customEnum.TextColor

object OutputHandler {

    fun colorCoatedMessage(message: String, color: TextColor) {
        println("$color$message${TextColor.RESET}")
    }

}