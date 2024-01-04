package database

import library.*
import library.customEnum.*
import modules.*
import java.sql.*

object DBServices {
    private val connection: Connection = DBConnection.connection()!! //get database connection
    fun addNewPassenger(passenger: Passenger): DBResponse {
        try {
            val userId = CREATE.insertUser(passenger)
            val aadhaarId = CREATE.insertAadhaar(passenger.aadhaar)
            CREATE.insertPassenger(userId, aadhaarId, passenger.preferredVehicleType)
        } catch (e: SQLException) {
            return DBResponse.OperationUnsuccessful(e.message)
        }
        return DBResponse.SuccessfullyCreated
    }

//    fun addNewDriver(
//        userName: String,
//        password: String,
//        name: String,
//        age: Int,
//        email: String,
//        phone: Long,
//        licenseNumber: String,
//        vehicleType: PreferredBike,
//        rcNumber: String
//    ) {
////        val userId = createNewUser(userName, password, name, age, email, phone)
//        val newBiker: PreparedStatement =
//            connection.prepareStatement("insert into ${DBTables.drivers.name} (`userId`,`licenseNumber`,`vehicleType`,`rcNumber`)value(?,?,?,?)")
//
////        newBiker.setInt(1, userId)
//        newBiker.setString(2, licenseNumber)
//        newBiker.setString(3, vehicleType.toString())
//        newBiker.setString(4, rcNumber)
//        newBiker.executeUpdate()
//        InputHandler.colorCoatedMessage("Successfully Driver Account created", TextColor.Green)
//    }
//


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

    fun getUser(userName: String): AuthenticationResponse {
        return READ.getUser(userName)
    }

    fun getLoggedUser(response: AuthenticationResponse.UserLoggedIn): User {
        return READ.getUserType(response)
    }

    fun createNewBooking(ride: Ride, passengerId: Int): DBResponse {
        return CREATE.insertRide(ride, passengerId)
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
}