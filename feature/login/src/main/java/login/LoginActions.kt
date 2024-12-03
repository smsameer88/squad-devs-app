package login

internal interface LoginActions {
    fun updateEmail(newValue:String)
    fun updatePassword(newValue:String)
    fun login()
}