package base

import UI_Layer.UIService
import library.*

fun main() {
    //UserInterface.displayWelcomeMessage()
    while (appRunStatus) {
        UIService.displayMainMenu()
        when (InputHandler.getInt(1, 3)) {
            1 -> UIService.signUp()
            2 -> UIService.signIn()
            3 -> appRunStatus = false
        }
    }
}