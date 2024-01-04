package UI_Layer

import database.DBServices
import library.*
import library.OutputHandler.colorCoatedMessage
import library.customEnum.*
import modules.Aadhaar
import modules.Passenger
import modules.User

internal object SignUpPage {
    private var age: Int? = null
    lateinit var user: User
    lateinit var aadhaar: Aadhaar


    fun displayPassengerSignUp(): DBResponse {
        age = InputHandler.getInt(15, 80, "Enter Your Age (15 - 80 only eligible)")
        user = displayUserSignup()
        aadhaar = gatherAadhaarData()
        val preferredVehicleType =
            InputHandler.getPreferredVehicleType("choose your preferred Vehicle type(SCOOTER/CLASSIC/SPORTS)")
        val passenger =
            Passenger(user.username, user.password, user.name, user.age, user.phone, aadhaar, preferredVehicleType)
        return DBServices.addNewPassenger(passenger)
    }


    fun displayDriverSignUp(): DBResponse {
        var licenseNumber: String
        var rcNumber: String

        age = InputHandler.getInt(21, 40, "Enter Your Age (21 - 40 only eligible)")
        val user: User = displayUserSignup()
        while (true) {
            licenseNumber = InputHandler.getString(6, 12, "Enter License number").uppercase()
            if (LocalRegex.LICENSE_PATTERN.code.matches(licenseNumber)) break
            else colorCoatedMessage("Invalid Format", TextColor.YELLOW)
        }

        val vehicleType = InputHandler.getPreferredVehicleType("choose your Vehicle type(SCOOTER/CLASSIC/SPORTS)")

        while (true) {
            rcNumber = InputHandler.getString(1, 10, "Enter RC Book number").uppercase()
            if (LocalRegex.RC_PATTERN.code.matches(rcNumber)) break
            else colorCoatedMessage("Invalid Format", TextColor.YELLOW)
        }

        // DBServices.addNewDriver()
        return DBResponse.SuccessfullyCreated
    }

    private fun gatherAadhaarData(): Aadhaar {
        var aadhaarNo: String
        while (true) {
            aadhaarNo = InputHandler.getString(14,14, "enter your aadhaar number format('XXXX-XXXX-XXXX')")
            if (LocalRegex.AADHAAR_NUMBER_PATTERN.code.matches(aadhaarNo)) break else colorCoatedMessage(
                "Invalid Format",
                TextColor.YELLOW
            )
        }
        val name: String = InputHandler.getString(3, 50, "Enter name as in your Aadhaar", "Invalid input..!")
        lateinit var renewalDate: String
        while (true) {
            renewalDate = InputHandler.getString(10, 10, "enter aadhaar renewal date format(DD/MM/YYY)")
            if (LocalRegex.DATE_PATTERN.code.matches(renewalDate)) break else colorCoatedMessage(
                "Invalid Format",
                TextColor.YELLOW
            )
        }
        return Aadhaar(aadhaarNo, name, renewalDate)
    }

    private fun displayUserSignup(): User {
        lateinit var username: String
        lateinit var password: String
        lateinit var name: String
        var response: Boolean
        while (true) {
            username = InputHandler.getString(3, 12, "Enter username")
            response = DBServices.isUserNameExist(username) // check userName already exist in database
            if (LocalRegex.USERNAME_PATTERN.code.matches(username)) {
                if (response) colorCoatedMessage("User name already exist", TextColor.YELLOW) else break
            } else colorCoatedMessage("Invalid Format Note:(cannot start with numbers, size 3-12, no space))", TextColor.YELLOW)
        }
        while (true) {
            password = InputHandler.getString(8, 15, "Enter your password")
            if (LocalRegex.PASSWORD_PATTERN.code.matches(password)) break else colorCoatedMessage(
                "Invalid Format (must contain(one uppercase, one lowercase, one specialCharacter,size 8-15)",
                TextColor.YELLOW
            )
        }
        while (true) {
            name = InputHandler.getString(3, 50, "Enter your full name")
            if (LocalRegex.NAME_PATTERN.code.matches(name)) break else colorCoatedMessage(
                "Invalid Format Note:(with surname eg:'jack sparrow')",
                TextColor.YELLOW
            )
        }
        val phone: Long = InputHandler.getLong(10, "Enter Mobile Number", "Enter valid mobile number..!")
        return User(username, password, name, age!!, phone)
    }


}