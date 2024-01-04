package modules

import UI_Layer.UIService
import library.customEnum.DBTables

open class User(
    val username: String,
    var password: String,
    val name: String,
    val age: Int,
    val phone: Long,
){
    fun displayMyRide(id: Int, table: DBTables) {
        UIService.displayMyRide(id, table)
    }
}
