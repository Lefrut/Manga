package com.dashkevich.signup

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.dashkevich.signup.databinding.FragmentSignUpBinding
import com.dashkevich.signup.model.ButtonPress
import com.dashkevich.signup.model.SignUpIntent
import com.dashkevich.ui.getColorFromAttr
import com.dashkevich.ui.setRegisterButtons
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private lateinit var binding: FragmentSignUpBinding
    private val signUpViewModel: SignUpViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)
        setRegisterButtons(view)
        val buttonContinue = binding.buttonContinue
        val haveAccount = binding.haveAccount
        val login = binding.login
        val password = binding.password
        val copyPassword = binding.copyPassword

        haveAccount.setOnClickListener {
            findNavController().popBackStack()
        }
        login.doOnTextChanged { text, _, _, _ ->
            signUpViewModel
                .processingEvent(SignUpIntent.ChangingLogin(text.toString()))
        }

        password.doOnTextChanged { text, _, _, _ ->
            signUpViewModel
                .processingEvent(SignUpIntent.ChangingPassword(text.toString()))

        }

        copyPassword.doOnTextChanged { text, _, _, _ ->
            signUpViewModel
                .processingEvent(
                    SignUpIntent.ChangingCopyPassword(
                        password.text.toString(),
                        text.toString()
                    )
                )
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                signUpViewModel.viewState.collect { state ->

                    when (state.buttonPress) {
                        ButtonPress.Press -> {
                            Log.d("Debug", "Button press")
                        }
                        else -> {}
                    }

                    withContext(Dispatchers.Main) {
                        @ColorInt val buttonColor: Int
                        if (state.correctnessOfFields.first
                            && state.correctnessOfFields.second
                            && state.correctnessOfFields.third
                        ) {
                            buttonColor =
                                requireContext().getColorFromAttr(com.google.android.material.R.attr.colorOnPrimarySurface)
                            buttonContinue.backgroundTintList =
                                ColorStateList.valueOf(buttonColor)
                            signUpViewModel.processingEvent(
                                SignUpIntent.ChangingReadinessButton(
                                    true
                                )
                            )
                        } else {
                            buttonColor =
                                requireContext().getColorFromAttr(com.google.android.material.R.attr.colorOnSecondary)
                            buttonContinue.backgroundTintList =
                                ColorStateList.valueOf(buttonColor)
                            signUpViewModel.processingEvent(
                                SignUpIntent.ChangingReadinessButton(
                                    false
                                )
                            )
                        }
                    }
                }
            }
        }

        buttonContinue.setOnClickListener {
            if (signUpViewModel.viewState.value.buttonReadiness) {
                signUpViewModel.processingEvent(SignUpIntent.ContinueButtonClick)
            }
            findNavController().navigate(com.dashkevich.navigation.R.id.action_global_choice_genres)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        signUpViewModel.processingEvent(SignUpIntent.LeaveScreen)
    }
}