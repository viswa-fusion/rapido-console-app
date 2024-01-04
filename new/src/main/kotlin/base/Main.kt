package base

import UI_Layer.UIService
import database.READ
import library.*
import library.customEnum.DBTables
import modules.User

var appRunStatus = true
fun main() {
    UIService.displayWelcomeMessage()
    while (appRunStatus) {
        UIService.displayMainMenu()
        when (InputHandler.getInt(1, 3)) {
            1 -> UIService.signUp()
            2 -> UIService.signIn()
            3 -> appRunStatus = false
        }
    }
    appRunStatus = true
}