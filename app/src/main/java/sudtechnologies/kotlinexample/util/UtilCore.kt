package sudtechnologies.kotlinexample.util

import java.util.regex.Pattern

/**
 * @author SudTechnologies
 */
object UtilCore {
    fun validateField(field: String): Boolean {
        var state: Boolean = true
        state = field.isNotEmpty()
        return state
    }

    fun isEmailValid(email: String): Boolean {
        return Pattern.compile(
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }

    fun validatePhone(field: String): Boolean {
        var state: Boolean = true
        if (field.length != 9) {
            state = false
        }
        return state
    }

    fun confirmPassword(password: String, confirmPassword: String): Boolean {
        var state: Boolean = true
        if (password != confirmPassword){
            state = false
        }
        return state
    }
}