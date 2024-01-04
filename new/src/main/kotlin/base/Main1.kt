package base

import UI_Layer.UIService
import library.*
import library.customEnum.TextColor

fun main() {
    UIService.displayWelcomeMessage()
    while (appRunStatus) {
        UIService.displayMainMenu()
        when (InputHandler.getInt(1, 3)) {
            1 -> UIService.signUp()
            2 -> UIService.signIn()
            3 -> closeApp()
        }
    }
    appRunStatus = true
}
