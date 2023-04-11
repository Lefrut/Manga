package com.dashkevich.signup.model

sealed class SignUpIntent {

    class ChangingLogin(val value: String): SignUpIntent()
    class ChangingPassword(val value: String): SignUpIntent()
    class ChangingCopyPassword(val value1: String, val value2: String): SignUpIntent()
    object ContinueButtonClick : SignUpIntent()
    class ChangingReadinessButton(val value: Boolean): SignUpIntent()
    object LeaveScreen : SignUpIntent()


}