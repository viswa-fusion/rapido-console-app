package modules

import uilayer.BookingPage
import database.DbService
import library.DBResponse
import library.customenum.*


class Passenger(
    username: String,
    password: String,
    name: String,
    age: Int,
    phone: Long,
    val aadhaar: Aadhaar,
    var preferredVehicleType: BikeType
) : User(username, password, name, age, phone) {

    fun bookRide(passengerId: Int) : DBResponse {
        return DbService.createNewBooking(BookingPage.gatherRideData(), passengerId)
    }

    override fun toString(): String {
        return "Passenger($username $password $name $age $phone aadhaar=${aadhaar.aadhaarNo} ${aadhaar.name}, preferredVehicleType=$preferredVehicleType)"
    }

}

