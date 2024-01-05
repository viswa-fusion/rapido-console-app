package uilayer

import base.*
import library.*
import library.customenum.*
import database.*
import library.OutputHandler.colorCoatedMessage
import modules.*

object UiService {
    fun signUp() {
        displaySelectUserMenu()
        val response: DBResponse = when (InputHandler.getInt(1, 2)) {
            1 -> SignUpPage.displayPassengerSignUp()
            2 -> SignUpPage.displayDriverSignUp()
            else -> DBResponse.SignupFailed
        }
        if (response.getResponse() == 200) {
            displayResponse(response, TextColor.GREEN)
            if (InputHandler.getString("enter 0 to login or press any key to exit", includeNull = true) == "0") signIn()
        } else {
            displayResponse(response, TextColor.RED)
            closeApp()
        }
    }

    fun signIn() {
        var dbResponse: DBResponse
        val loggedUser: User = SignInPage.displaySignInPage()
        val loggedUserid = DbService.getTablePrimaryKey(DbTables.passengers, loggedUser)
        when (loggedUser) {

            is Passenger -> {
                println("welcome ${loggedUser.name}")
                while (true) {
                    displayPassengerMainMenu()
                    when (InputHandler.getInt(1, 4)) {
                        1 -> {
                            dbResponse = loggedUser.bookRide(loggedUserid)
                            displayResponse(dbResponse, TextColor.GREEN)
                        }

                        2 -> loggedUser.displayMyRide(loggedUserid, DbTables.passengers)

                        4 -> break
                    }
                }
            }

            is Driver -> {
                println("Welcome ${loggedUser.name}")
                while(true){
                    displayDriverMainMenu()
                    when (InputHandler.getInt(1, 4)) {
                        1 -> {
                            DbService.getNearByAvailableRide(RidePage.gatherSourceLocation())
                        }
                        4 -> {
                            InputHandler.getString("enter 0 to confirm logout or press any key to cancel", includeNull = true)
                            break
                        }
                    }
                }
            }
        }
    }


    fun displayMainMenu() {
        colorCoatedMessage(
            """1 -> Sign_Up
            |2 -> Sign_In
            |3 -> Exit""".trimMargin("|"), TextColor.PEACH
        )
    }

    private fun displaySelectUserMenu() {
        colorCoatedMessage(
            """1 -> Passenger
            |2 -> Driver""".trimMargin("|"), TextColor.PEACH
        )
    }

    private fun displayPassengerMainMenu() {
        colorCoatedMessage(
            """1 -> Book Ride
                |2 -> My Ride
                |3 -> View Profile
                |4 -> Logout
            """.trimMargin("|"), TextColor.PEACH
        )
    }

    private fun displayDriverMainMenu(){
        colorCoatedMessage(
            """1 -> Check Available Ride
                |2 -> My Ride
                |3 -> View Profile
                |4 -> Logout
            """.trimMargin("|"), TextColor.PEACH
        )
    }

    fun displayRideRotes() {
        colorCoatedMessage(UtilContent.map, TextColor.MAGENTA)
    }

    fun displayWelcomeMessage() {
        colorCoatedMessage(UtilContent.welcomeMessage, TextColor.BLUE)
    }

    fun displayMyRide(id: Int, table: DbTables) {
        println(DbService.getMyRide(id, table))
    }

    fun displayResponse(ob: Any, textColor: TextColor) {
        when (ob) {
            is DBResponse -> {
                colorCoatedMessage("${ob.getResponse()} -> ${ob.getResponseMessage()}", textColor)
            }

            is AuthenticationResponse -> {
                colorCoatedMessage("${ob.getResponse()} -> ${ob.getResponseMessage()}", textColor)
            }
        }
    }

}