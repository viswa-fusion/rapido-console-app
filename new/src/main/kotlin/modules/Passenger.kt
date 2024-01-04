package modules

import UI_Layer.BookingPage
import database.DBServices
import library.DBResponse
import library.customEnum.*


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
        return DBServices.createNewBooking(BookingPage.gatherRideData(), passengerId)
    }

    override fun toString(): String {
        return "Passenger($username $password $name $age $phone aadhaar=${aadhaar.aadhaarNo} ${aadhaar.name}, preferredVehicleType=$preferredVehicleType)"
    }

}

