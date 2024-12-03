package login


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import extensions.update
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel(), LoginActions {

    private val emailValidator = validation.EmailValidator()
    private val passwordValidator = validation.PasswordValidator()

    val screenState: StateFlow<LoginScreenState> =
        savedStateHandle.getStateFlow(LOGIN_SCREEN_STATE, LoginScreenState())

    override fun updateEmail(newValue: String) {
        savedStateHandle.update<LoginScreenState>(LOGIN_SCREEN_STATE) {
            it.copy(email = newValue, isWrongEmailFormat = false)
        }
    }

    override fun updatePassword(newValue: String) {
        savedStateHandle.update<LoginScreenState>(LOGIN_SCREEN_STATE) {
            it.copy(password = newValue, isWrongPasswordFormat = false)
        }
    }

    override fun login() {
        val email = screenState.value.email
        val password = screenState.value.password
        if (!emailValidator.validateEmail(email)) {
            setIncorrectEmailFormatError()
        } else if (!passwordValidator.validatePassword(password)) {
            setIncorrectPasswordFormat()
        } else {
            proceedLoggingIn(email, password)
        }
    }

    private fun proceedLoggingIn(email: String, password: String) {

    }

    private fun setIncorrectEmailFormatError() {
        savedStateHandle.update<LoginScreenState>(LOGIN_SCREEN_STATE) {
            it.copy(isWrongEmailFormat = true)
        }
    }

    private fun setIncorrectPasswordFormat() {
        savedStateHandle.update<LoginScreenState>(LOGIN_SCREEN_STATE) {
            it.copy(isWrongPasswordFormat = true)
        }
    }

    companion object {
        const val LOGIN_SCREEN_STATE = "loginScreenStateKey"
    }
}