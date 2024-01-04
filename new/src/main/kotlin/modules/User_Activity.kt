package modules

import library.customEnum.Log_Status
import modules.User

data class User_Activity(val user: User, val stats: Log_Status, val time: String)