package com.dashkevich.signup.model

data class SignUpState(
    val buttonReadiness: Boolean = false,
    val correctnessOfFields: Triple<Boolean, Boolean, Boolean> =
        Triple(false, false, false),
    val buttonPress: ButtonPress = ButtonPress.None
)