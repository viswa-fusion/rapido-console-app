package UI_Layer

import library.*

internal object SignInPage {
    private lateinit var userName: String
    private lateinit var password: String
    fun displaySignInPage(): AuthenticationResponse {
        userName = InputHandler.getString(3, 12, "Enter username")
        password = InputHandler.getString(8, 15, "Enter password")
        return Authenticator.authenticate(userName, password)
    }
}