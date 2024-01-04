package library.customEnum

enum class PreferredBike {
    SCOOTER,CLASSIC,SPORTS;

}
fun castToPreferredBike(s:String) : PreferredBike?{
    return when(s) {
        "SCOOTER" -> PreferredBike.SCOOTER
        "CLASSIC" -> PreferredBike.CLASSIC
        "SPORTS"  -> PreferredBike.SPORTS
        else -> null
    }
}