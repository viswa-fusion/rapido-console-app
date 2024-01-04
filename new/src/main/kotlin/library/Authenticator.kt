package library
import database.*
object Authenticator {
    fun authenticate(userName : String, password : String) : AuthenticationResponse{
        return when(val response = DBServices.isValidCredential(userName,password)){
            is AuthenticationResponse.UserFound -> DBServices.getUser(userName)
            else -> response
        }
    }
}