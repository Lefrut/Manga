package com.dashkevich.signup

import androidx.lifecycle.ViewModel
import com.dashkevich.signup.model.ButtonPress
import com.dashkevich.signup.model.SignUpIntent
import com.dashkevich.signup.model.SignUpState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignUpViewModel() : ViewModel() {

    private val _viewState: MutableStateFlow<SignUpState> = MutableStateFlow(SignUpState())
    val viewState = _viewState.asStateFlow()


    fun processingEvent(event: SignUpIntent) {
        when (event) {
            SignUpIntent.ContinueButtonClick -> {
                continueButtonClick()
            }
            is SignUpIntent.ChangingCopyPassword -> {
                copyPasswordVerification(event.value1, event.value2)
            }
            is SignUpIntent.ChangingLogin -> {
                loginVerification(event.value)
            }
            is SignUpIntent.ChangingPassword -> {
                passwordVerification(event.value)

            }
            is SignUpIntent.ChangingReadinessButton -> {
                readinessButtonChange(event.value)
            }
            SignUpIntent.LeaveScreen -> {
                leaveScreen()
            }
        }
    }

    private fun leaveScreen() {
        _viewState.value = viewState.value.copy(
            buttonPress = ButtonPress.None
        )
    }

    private fun readinessButtonChange(readinessButton: Boolean) {
        _viewState.value = viewState.value.copy(
            buttonReadiness = readinessButton
        )
    }

    private fun passwordVerification(password: String) {
        val correctnessOfFields = viewState.value.correctnessOfFields.copy(
            second = authVerification(password)
        )
        _viewState.value = viewState.value.copy(
            correctnessOfFields = correctnessOfFields
        )
    }

    private fun copyPasswordVerification(password: String, copyPassword: String) {
        val correctnessOfFields = viewState.value.correctnessOfFields.copy(
            third = (copyPassword.isNotEmpty() && password == copyPassword)
        )
        _viewState.value = viewState.value.copy(
            correctnessOfFields = correctnessOfFields
        )
    }

    private fun loginVerification(login: String) {
        val correctnessOfFields = viewState.value.correctnessOfFields.copy(
            first = authVerification(login)
        )
        _viewState.value = viewState.value.copy(
            correctnessOfFields = correctnessOfFields
        )
    }

    private fun continueButtonClick() {
        _viewState.value = viewState.value.copy(
            buttonPress = ButtonPress.Press
        )
    }

    private fun authVerification(str: String): Boolean {
        return str.isNotEmpty()
    }
}