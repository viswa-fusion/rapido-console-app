package UI_Layer

import database.DBServices
import library.*
import modules.User

internal object SignInPage {
    private lateinit var userName: String
    private lateinit var password: String
    fun displaySignInPage(): User? {
        while (true) {
            userName = InputHandler.getString(3, 12, "Enter username")
            password = InputHandler.getString(8, 15, "Enter password")
            val response = Authenticator.authenticate(userName, password)
            return if (response is AuthenticationResponse.UserLoggedIn) DBServices.getUserType(response) else null
        }
    }
}