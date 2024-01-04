package modules

import library.customEnum.RideStatus

data class Ride(
    val passenger: Passenger? = null,
    val driver: Driver? = null,
    val pickup_location:String,
    val drop_location: String,
    val start_time: String?=null,
    val end_time: String? = null,
    val status: RideStatus,
    val total_charge: Double = 0.0
)