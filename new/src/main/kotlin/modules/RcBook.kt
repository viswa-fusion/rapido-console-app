package modules

data class RcBook(
    val ownerName: String,
    var model: String,
    var bikeType: String,
    val bikeColor: String,
    val validFrom: String,
    var validTill: String,
    val regNo: String
)
