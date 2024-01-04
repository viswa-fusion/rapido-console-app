package UI_Layer

import base.*
import library.*
import library.customEnum.*
import database.*
import library.OutputHandler.colorCoatedMessage
import modules.Driver
import modules.Passenger
import modules.User

object UIService {
    fun signUp() {
        displaySelectUserMenu()
        val response: DBResponse = when (InputHandler.getInt(1, 2)) {
            1 -> SignUpPage.displayPassengerSignUp()
            2 -> SignUpPage.displayDriverSignUp()
            else -> DBResponse.SignupFailed
        }
        if (response.getResponse() == 200) {
            displayResponse(response, TextColor.GREEN)
            if (InputHandler.getString("enter 0 to login or press any key to exit") == "0") {
                signIn()
            } else closeApp()
        } else {
            displayResponse(response, TextColor.RED)
            closeApp()
        }
    }

    fun signIn() {
        var dbResponse: DBResponse
        val loggedUser: User? = SignInPage.displaySignInPage()
        if (loggedUser == null) closeApp()
        else {
            when (loggedUser) {
                is Passenger -> {
                    println("welcome ${loggedUser.name}")
                    val loggedUserid = DBServices.getTablePrimaryKey(DBTables.passengers, loggedUser)
                    while (true) {
                        displayPassengerMainMenu()
                        when (InputHandler.getInt(1, 4)) {
                            1 -> {
                                dbResponse = loggedUser.bookRide(loggedUserid)
                                displayResponse(dbResponse, TextColor.GREEN)
                            }

                            2 -> loggedUser.displayMyRide(loggedUserid, DBTables.passengers)

                            else -> {
                                closeApp()
                                break
                            }
                        }
                    }
                }

                is Driver -> {
                    println("Welcome ${loggedUser.name}")
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