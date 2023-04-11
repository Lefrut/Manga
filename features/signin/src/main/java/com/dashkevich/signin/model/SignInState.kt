package com.dashkevich.signin.model

data class SignInState(
    val buttonReadiness: Boolean = false,
    val correctnessOfFields: Pair<Boolean, Boolean> =
        Pair(false, false),
    val buttonPress: ButtonPress = ButtonPress.None
)