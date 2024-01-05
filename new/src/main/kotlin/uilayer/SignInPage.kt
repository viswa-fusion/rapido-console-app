package uilayer

import database.DbService
import library.*
import library.customenum.TextColor
import modules.User

internal object SignInPage {
    private lateinit var userName: String
    private lateinit var password: String
    fun displaySignInPage(): User {
        while (true) {
            userName = InputHandler.getString(3, 12, "Enter username")
            password = InputHandler.getString(8, 15, "Enter password")
            val response = Authenticator.authenticate(userName, password)
            if (response is AuthenticationResponse.UserLoggedIn) {
                return  DbService.getUserType(response)
            }else{
                UiService.displayResponse(response, TextColor.YELLOW)
            }
        }
    }
}