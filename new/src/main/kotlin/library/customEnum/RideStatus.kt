package library.customEnum

enum class RideStatus {
    BOOKED,COMPLETED,CANCEL,RIDE_START,RIDE_END,PAYMENT_PENDING
}
fun castToRideStatus(status: String):RideStatus?{
    return when(status){
        RideStatus.BOOKED.name -> RideStatus.BOOKED
        RideStatus.COMPLETED.name -> RideStatus.COMPLETED
        RideStatus.CANCEL.name -> RideStatus.CANCEL
        RideStatus.RIDE_START.name -> RideStatus.RIDE_START
        RideStatus.RIDE_END.name -> RideStatus.RIDE_END
        RideStatus.PAYMENT_PENDING.name -> RideStatus.PAYMENT_PENDING
        else -> null
    }
}