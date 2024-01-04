package library

import library.OutputHandler.colorCoatedMessage
import library.customEnum.PreferredBike
import library.customEnum.TextColor


object InputHandler {
    fun getInt(
        min: Int,
        max: Int,
        message: String = "enter your choice",
        retryMessage: String = "invalid input try again..!"
    ): Int {
        var num: Int?
        println(message)
        do {
            num = readLine()?.toIntOrNull()
        } while ((num == null || num !in min..max).also { if (it) colorCoatedMessage(retryMessage, TextColor.RED) })
        return num!!
    }

    fun getInt(
        message: String = "enter your choice",
        retryMessage: String = "invalid input try again..!"
    ):Int{
        var num: Int?
        println(message)
        do {
            num = readLine()?.toIntOrNull()
        } while ((num == null).also { if (it) colorCoatedMessage(retryMessage, TextColor.RED) })
        return num!!
    }

    fun getInt(
        size: Int,
        message: String = "enter your choice",
        retryMessage: String = "invalid input try again..!"
    ):Int{
        var num: Int?
        println(message)
        do {
            num = readLine()?.toIntOrNull()
        } while ((num == null || num.getSize() != size).also { if (it) colorCoatedMessage(retryMessage, TextColor.RED) })
        return num!!
    }

    fun getString(
        minSize: Int,
        maxSize: Int,
        message: String = "enter your input",
        retryMessage: String = "invalid input try again..!"
    ): String {
        var string: String?
        println(message)
        do {
            string = readLine()
        } while ((string == null || string.length !in minSize..maxSize).also {
                if (it) colorCoatedMessage(
                    retryMessage,
                    TextColor.RED
                )
            })
        return string!!
    }

    fun getString(
        message: String,
        retryMessage: String = "invalid input try again..!"
    ): String {
        var string: String?
        println(message)
        do {
            string = readLine()
        } while ((string == null).also {
                if (it) colorCoatedMessage(
                    retryMessage,
                    TextColor.RED
                )
            })
        return string!!
    }

    fun getLong( size: Long, message: String = "enter long number", retryMessage: String = "Invalid Input..!"): Long {
        var longNum: Long?
        println(message)
        do {
            longNum = readLine()?.toLongOrNull()
        } while ((longNum == null || longNum.geSize() != size).also { if (it) colorCoatedMessage(retryMessage, TextColor.RED) })
        return longNum!!
    }

    fun getPreferredVehicleType(message: String) : PreferredBike {
        var preferredVehicleType : PreferredBike?
        do{
            preferredVehicleType = when(getString(message).uppercase()){
                "SCOOTER" -> PreferredBike.SCOOTER
                "CLASSIC" -> PreferredBike.CLASSIC
                "SPORTS"  -> PreferredBike.SPORTS
                else -> null
            }
        }while((preferredVehicleType == null).also { if(it) colorCoatedMessage("Invalid Input..!", TextColor.YELLOW) })

        return preferredVehicleType!!
    }

    private fun Int.getSize(): Int{
        var count=0
        var temp =this
        while (temp>0){
            count++
            temp/=10
        }
        return count
    }

    private fun Long.geSize(): Long{
        var count=0L
        var temp =this
        while (temp>0){
            count++
            temp/=10
        }
        return count
    }
}