package library.customenum


enum class BikeType {
    SCOOTER,CLASSIC,SPORTS;

}
fun castToBikeType(s:String) : BikeType?{
    return when(s) {
        "SCOOTER" -> BikeType.SCOOTER
        "CLASSIC" -> BikeType.CLASSIC
        "SPORTS"  -> BikeType.SPORTS
        else -> null
    }
}