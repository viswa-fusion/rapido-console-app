package database

import library.*
import library.customEnum.*
import modules.Aadhaar
import modules.Ride
import modules.User
import java.sql.Connection
import java.sql.SQLException
import java.sql.Statement

internal object CREATE {
    private lateinit var query: String
    private val connection: Connection = DBConnection.connection()!! //get database connection

    fun insertUser(user: User) : Int{
        query ="insert into ${DBTables.users.name} (`username`,`password`,`name`,`age`,`phone`)value(?,?,?,?,?)"
        connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS).use {
            it.setString(1, user.username)
            it.setString(2, user.password)
            it.setString(3, user.name)
            it.setInt(4, user.age)
            it.setString(5, user.phone.toString())
            val result = it.executeUpdate()

            if (result == 0) {
                throw SQLException("Insert user failed, no rows affected.")
            }
            val generatedKeys = it.generatedKeys
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1)
            } else {
                throw SQLException("Insert user failed, no ID obtained.")
            }
        }
    }

    fun insertAadhaar(aadhaar: Aadhaar) : Int{
        query = "insert into ${DBTables.AADHAAR.name} (`aadhaar_no`,`name`,`renewal_date`) value(?,?,?)"
        connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS).use{
            it.setString(1,aadhaar.aadhaarNo)
            it.setString(2,aadhaar.name)
            it.setString(3,aadhaar.renewalDate)
            val result = it.executeUpdate()
            if (result == 0) {
                throw SQLException("Insert aadhaar failed, no rows affected.")
            }
            val generatedKeys = it.generatedKeys
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1)
            } else {
                throw SQLException("Insert aadhaar failed, no ID obtained.")
            }
        }
    }

    fun insertPassenger(userId: Int, aadhaarId: Int, preferredVehicleType: PreferredBike){
        query = "insert into ${DBTables.passengers.name} (`user_id`,`aadhaar_id`,`preferred_vehicle_type`)value(?,?,?)"
        connection.prepareStatement(query).use {
            it.setInt(1,userId)
            it.setInt(2,aadhaarId)
            it.setString(3,preferredVehicleType.toString())
            val result = it.executeUpdate()
            if (result == 0) {
                throw SQLException("insert passenger failed, no rows affected.")
            }
        }
    }

    fun insertRide(ride: Ride, passengerId: Int): DBResponse {
        query = "insert into ${DBTables.rides.name} (`passenger_id`,`pickup_location`,`drop_location`,`status`,`total_charge`)value(?,?,?,?,?)"
        connection.prepareStatement(query).use{
            it.setInt(1,passengerId)
            it.setString(2,ride.pickup_location)
            it.setString(3,ride.drop_location)
            it.setString(4, ride.status.toString())
            it.setDouble(5, ride.total_charge)
            val result = it.executeUpdate()
            if (result == 0) {
                return DBResponse.OperationUnsuccessful("unable to add $ride into ${DBTables.users.name} in insert ride function")
            }
        }
        return DBResponse.SuccessfullyCreated
    }

}
