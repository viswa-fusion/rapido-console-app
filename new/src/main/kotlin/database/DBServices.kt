package database

import library.*
import library.customEnum.*
import modules.*
import modules.Driver
import java.nio.charset.CodingErrorAction
import java.sql.*

object DBServices {
    private val connection: Connection = DBConnection.connection()!! //get database connection
    fun addNewPassenger(passenger: Passenger): DBResponse {
        return try {
            val userId = CREATE.insertUser(passenger)
            val aadhaarId = CREATE.insertAadhaar(passenger.aadhaar)
            CREATE.insertPassenger(userId, aadhaarId, passenger.preferredVehicleType)
            DBResponse.SuccessfullyCreated
        } catch (e: SQLException) {
            DBResponse.OperationUnsuccessful(e.message)
        }
    }

    fun addNewDriver(driver : Driver): DBResponse {
            val userId = CREATE.insertUser(driver)
            val licenseId = CREATE.insertLicense(driver.license)
            val bikeId = CREATE.insertBike(driver.bike)
            return CREATE.insertDriver(userId, licenseId, bikeId)
    }

    fun createNewBooking(ride: Ride, passengerId: Int): DBResponse {
        return CREATE.insertRide(ride, passengerId)
    }


    fun getUser(userName: String): AuthenticationResponse {
        return READ.getUser(userName)
    }

    fun getLoggedUser(response: AuthenticationResponse.UserLoggedIn): User {
        return READ.getUserType(response)
    }

    fun getTablePrimaryKey(tableName: DBTables, user: User): Int {
        return when (tableName) {
            DBTables.passengers -> READ.getPassengerTablePrimaryKey(user)
            DBTables.drivers -> -1
            else -> throw SQLException("no table available")
        }
    }

    fun getMyRide(id: Int, table: DBTables): Ride {
        return READ.getMyRide(id, table)!!
    }

    fun getUserType(response: AuthenticationResponse.UserLoggedIn): User {
        return READ.getUserType(response)
    }

    fun isUserNameExist(userName: String): Boolean {
//      return isUsernameExist(username,"userCredential") || isUsernameExist(username,"bikerCredential")
        return READ.isUserNameExist(userName, DBTables.users)
    }

    fun isValidCredential(userName: String, password: String): AuthenticationResponse {
        if (isUserNameExist(userName))
            return if (READ.checkPassword(userName, password)) {
                AuthenticationResponse.UserFound
            } else AuthenticationResponse.InvalidPassword
        return AuthenticationResponse.InvalidUsername
    }
}