package library

import modules.User

interface Response {
    fun getResponse() : Int
    fun getResponseMessage() : String
}
sealed class DBResponse(private val responseCode: Int, private val responseMessage: String) : Response{

    object SuccessfullyCreated : DBResponse(200, "INSERT successfully")
    object SuccessfullyDeleted : DBResponse(200, "DELETE successfully")
    data class OperationUnsuccessful(val message: String?) : DBResponse(401, "Operation Failed")
    object ConnectionFailed : DBResponse(404, "Database connection failed")
    object SignupFailed : DBResponse(404, "unable to signup now try again later")

    override fun getResponse() : Int{
        return responseCode
    }

    override fun getResponseMessage() : String{
        return responseMessage
    }
}

sealed class AuthenticationResponse(private val responseCode: Int, private val responseMessage: String) : Response{

    data class UserLoggedIn(val userId: Int, val user: User) : AuthenticationResponse(200 , "Successfully logged in")
    object UserFound : AuthenticationResponse(200 , "user available")
    object UserNotFound : AuthenticationResponse(404, "User not found")
    object InvalidUsername : AuthenticationResponse(401, "Invalid Username")
    object InvalidPassword : AuthenticationResponse(401, "Invalid Password")

    override fun getResponse() : Int{
        return responseCode
    }

    override fun getResponseMessage() : String{
        return responseMessage
    }
}

