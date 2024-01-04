package UI_Layer

import base.*
import library.*
import library.customEnum.*
import database.*
import library.OutputHandler.colorCoatedMessage
import modules.Driver
import modules.Passenger

object UIService {
    fun signUp() {
        displaySelectUserMenu()
        val response: DBResponse = when (InputHandler.getInt(1, 2)) {
            1 -> SignUpPage.displayPassengerSignUp()
            2 -> SignUpPage.displayDriverSignUp()
            else -> DBResponse.SignupFailed
        }
        if (response.getResponse() == 200) {
            displayResponse(response, TextColor.Green)
            if (InputHandler.getString("enter 0 to login or press any key to exit") == "0") {
                signIn()
            } else appRunStatus = false
        } else {
            displayResponse(response, TextColor.RED)
            appRunStatus = false
        }
    }

    fun signIn() {
        var dbResponse: DBResponse
        when (val response = (SignInPage.displaySignInPage())) {
            is AuthenticationResponse.UserLoggedIn -> {
                when (val loggedUser = DBServices.getLoggedUser(response)) {
                    is Passenger -> {
                        println("welcome ${loggedUser.name}")
                        val loggedUserid = DBServices.getTablePrimaryKey(DBTables.passengers, loggedUser)
                        while(true){
                            displayPassengerMainMenu()
                            when (InputHandler.getInt(1, 4)) {
                                1 -> {
                                    dbResponse = loggedUser.bookRide(loggedUserid)
                                    displayResponse(dbResponse, TextColor.Green)
                                }

                                2 -> loggedUser.displayMyRide(loggedUserid, DBTables.passengers)

                                else -> {
                                    appRunStatus = false
                                    break
                                }
                            }
                        }
                    }

                    is Driver -> {

                    }
                }
            }

            else -> displayResponse(response, TextColor.RED)
        }
    }

    fun displayMainMenu() {
        println(
            """1 -> Sign_Up
            |2 -> Sign_In
            |3 -> Exit""".trimMargin("|")
        )
    }

    private fun displaySelectUserMenu() {
        println(
            """1 -> Passenger
            |2 -> Driver""".trimMargin("|")
        )
    }

    private fun displayPassengerMainMenu() {
        println(
            """1 -> Book Ride
                |2 -> My Ride
                |3 -> View Profile
                |4 -> Logout
            """.trimMargin("|")
        )
    }

    fun displayRideRotes() {
        val map =
            """                         
                                         |<--------> Mudichor(MU) <------>|                                   |<-----------> Pammal(PM)
                Guduvancheri(GU) <--> Vandalur(VA)                   Tambaram(TM) <--> Chrompet(CH) <--> Pallavaram(PA) <--> InternationalAirport(CIA)
                                         |<------> Perungalathur(PE) <--->|
                                         
            """.trimIndent()
        colorCoatedMessage(map, TextColor.MAGENTA)
    }

    fun displayWelcomeMessage() {
        val welcomeMessage = """
                                                                                               |-|
        __        __   _                              _________        ____         _ __ (_)   | |
        \ \      / /__| | ___ ___  _ __ ___   __     |___   ___|      |  _ \  __ _ | '_ \ _  __. | ___
         \ \ /\ / / _ \ |/ __/ _ \| '_ ` _ \ / _ \       | |/ _ \     | |_) |/ _` || |_) | |/ _  |/ _ \
          \ V  V /  __/ | (_| (_) | | | | | |  __/       | | (_) |    |  _ <| (_| || .__/| | (_| | (_) |
           \_/\_/ \___|_|\___\___/|_| |_| |_|\___|       |_|\___/     |_| \_\\__,_|| |   |_|\__'_|\___/ 
                                                                                   |_| 
    """.trimIndent()

        colorCoatedMessage(welcomeMessage, TextColor.BLUE)
    }

    fun displayMyRide(id: Int, table: DBTables) {
        println(DBServices.getMyRide(id, table))
    }

    fun displayResponse(ob: Any, textColor: TextColor) {
        when (ob) {
            is DBResponse -> {
                colorCoatedMessage("${ob.getResponse()} -> ${ob.getResponseMessage()}", textColor)
            }
        }
    }

}