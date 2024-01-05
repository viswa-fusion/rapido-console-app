package backend

import library.customenum.Location

class UtilityFunction private constructor(){
    companion object{
        fun initializeRapidoMap(){
            Location.GU.addDestination(Location.VA, 10)
            Location.VA.addDestination(Location.MU, 2)
            Location.VA.addDestination(Location.PE, 7)
            Location.MU.addDestination(Location.TM, 2)
            Location.PE.addDestination(Location.TM, 8)
            Location.TM.addDestination(Location.CH, 3)
            Location.CH.addDestination(Location.PA, 6)
            Location.PA.addDestination(Location.PM, 3)
            Location.PA.addDestination(Location.CIA, 4)
        }
    }
}