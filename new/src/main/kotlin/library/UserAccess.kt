package library

import UI_Layer.UIService

class UserAccess : PassengerAccess, DriverAccess{

    fun getMap(){
        UIService.displayRideRotes()
    }

    fun bookRide(){

    }
}