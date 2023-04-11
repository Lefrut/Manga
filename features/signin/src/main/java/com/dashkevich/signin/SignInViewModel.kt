package com.dashkevich.signin

import androidx.lifecycle.ViewModel
import com.dashkevich.signin.model.ButtonPress
import com.dashkevich.signin.model.SignInIntent
import com.dashkevich.signin.model.SignInState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignInViewModel() : ViewModel() {

    private val _viewState: MutableStateFlow<SignInState> = MutableStateFlow(SignInState())
    val viewState = _viewState.asStateFlow()


    fun processingEvent(event: SignInIntent) {
        when (event) {
            SignInIntent.ContinueButtonClick -> {
                continueButtonClick()
            }
            is SignInIntent.ChangingLogin -> {
                loginVerification(event.value)
            }
            is SignInIntent.ChangingPassword -> {
                passwordVerification(event.value)

            }
            is SignInIntent.ChangingReadinessButton -> {
                readinessButtonChange(event.value)
            }
            SignInIntent.LeaveScreen -> {
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