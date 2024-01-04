package database

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object DBConnection {
    private const val url = "jdbc:mysql://localhost:3306/rapido"
    private const val user = "root"
    private const val password = "Hicet@123"
    fun connection(): Connection? {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver")
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }

        val connection: Connection? = null
        try {
            return DriverManager.getConnection(url, user, password)
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return connection
    }


}
