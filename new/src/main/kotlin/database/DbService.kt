package database

import library.*
import library.customenum.*
import modules.*
import modules.Driver
import java.sql.*

object DbService {
    private val connection: Connection = DbConnection.connection()!! //get database connection
    fun addNewPassenger(passenger: Passenger): DBResponse {
        return try {
            val userId = Create.insertUser(passenger)
            val aadhaarId = Create.insertAadhaar(passenger.aadhaar)
            Create.insertPassenger(userId, aadhaarId, passenger.preferredVehicleType)
            DBResponse.SuccessfullyCreated
        } catch (e: SQLException) {
            DBResponse.OperationUnsuccessful(e.message)
        }
    }

    fun addNewDriver(driver : Driver): DBResponse {
            val userId = Create.insertUser(driver)
            val licenseId = Create.insertLicense(driver.license)
            val bikeId = Create.insertBike(driver.bike)
            return Create.insertDriver(userId, licenseId, bikeId)
    }

    fun createNewBooking(ride: Ride, passengerId: Int): DBResponse {
        return Create.insertRide(ride, passengerId)
    }


    fun getUser(userName: String): AuthenticationResponse {
        return Read.getUser(userName)
    }

    fun getLoggedUser(response: AuthenticationResponse.UserLoggedIn): User {
        return Read.getUserType(response)
    }

    fun getTablePrimaryKey(tableName: DbTables, user: User): Int {
        return when (tableName) {
            DbTables.passengers -> Read.getPassengerTablePrimaryKey(user)
            DbTables.drivers -> -1
            else -> throw SQLException("no table available")
        }
    }

    fun getMyRide(id: Int, table: DbTables): Ride {
        return Read.getMyRide(id, table)!!
    }

    fun getUserType(response: AuthenticationResponse.UserLoggedIn): User {
        return Read.getUserType(response)
    }

    fun isUserNameExist(userName: String): Boolean {
//      return isUsernameExist(username,"userCredential") || isUsernameExist(username,"bikerCredential")
        return Read.isUserNameExist(userName, DbTables.users)
    }

    fun isValidCredential(userName: String, password: String): AuthenticationResponse {
        if (isUserNameExist(userName))
            return if (Read.checkPassword(userName, password)) {
                AuthenticationResponse.UserFound
            } else AuthenticationResponse.InvalidPassword
        return AuthenticationResponse.InvalidUsername
    }

    fun getNearByAvailableRide(RadiusInKiloMeter : Int) : List<Ride>{
        return Read.getNearByAvailableRide(RadiusInKiloMeter)
    }
}