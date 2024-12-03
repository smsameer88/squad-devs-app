package login

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginScreenState(
    val email: String = "",
    val password: String = "",
    val loggedInUser: String? = null,
    val wrongCredentials: Boolean = false,
    val isWrongEmailFormat: Boolean = false,
    val isWrongPasswordFormat: Boolean = false
) : Parcelable