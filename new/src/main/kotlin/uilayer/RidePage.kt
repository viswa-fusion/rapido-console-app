package uilayer

import library.InputHandler
import library.OutputHandler
import library.customenum.Location
import library.customenum.TextColor
import library.customenum.castToLocation

internal object RidePage {
    fun gatherSourceLocation(): Location {
        while (true) {
            val location = castToLocation(InputHandler.getString(2, "enter your current location"))
            if (location != null) return location
            OutputHandler.colorCoatedMessage("invalid Location..!  \\uD83D\\uDE22", TextColor.RED)
        }
    }

}