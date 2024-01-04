package UI_Layer

import library.*
import library.customEnum.RideStatus
import modules.Ride

internal object BookingPage {

    fun gatherRideData(): Ride {
        UIService.displayRideRotes()
        val pickupLocation = InputHandler.getString(2,2,"enter pickup location (eg:GU)").uppercase()
        val dropLocation = InputHandler.getString(2,2,"enter drop location (eg:GU)").uppercase()

        return Ride(pickup_location = pickupLocation, drop_location = dropLocation, status = RideStatus.BOOKED)
    }

}