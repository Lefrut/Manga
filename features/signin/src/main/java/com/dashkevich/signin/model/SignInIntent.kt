package com.dashkevich.signin.model

sealed class SignInIntent {

    class ChangingLogin(val value: String): SignInIntent()
    class ChangingPassword(val value: String): SignInIntent()
    object ContinueButtonClick : SignInIntent()
    class ChangingReadinessButton(val value: Boolean): SignInIntent()
    object LeaveScreen : SignInIntent()

}