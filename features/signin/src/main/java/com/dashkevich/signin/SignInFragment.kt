package com.dashkevich.signin

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.core.text.color
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.dashkevich.signin.databinding.FragmentSignInBinding
import com.dashkevich.signin.model.ButtonPress
import com.dashkevich.signin.model.SignInIntent
import com.dashkevich.ui.getColorFromAttr
import com.dashkevich.ui.setRegisterButtons
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    lateinit var binding: FragmentSignInBinding
    private val signInViewModel: SignInViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)
        binding.haveAccount.setHaveAccountText()
        setRegisterButtons(view)


        val buttonContinue = binding.buttonContinue
        val haveAccount = binding.haveAccount
        val login = binding.login
        val password = binding.password

        haveAccount.setOnClickListener {
            findNavController().navigate(com.dashkevich.navigation.R.id.action_global_sign_up)
        }
        login.doOnTextChanged { text, _, _, _ ->
            signInViewModel
                .processingEvent(SignInIntent.ChangingLogin(text.toString()))
        }

        password.doOnTextChanged { text, _, _, _ ->
            signInViewModel
                .processingEvent(SignInIntent.ChangingPassword(text.toString()))

        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                signInViewModel.viewState.collect { state ->

                    when (state.buttonPress) {
                        ButtonPress.Press -> {
                            findNavController().navigate(com.dashkevich.navigation.R.id.action_global_sign_up)
                        }
                        else -> {}
                    }

                    withContext(Dispatchers.Main) {
                        @ColorInt val buttonColor: Int
                        if (state.correctnessOfFields.first
                            && state.correctnessOfFields.second
                        ) {
                            buttonColor =
                                requireContext().getColorFromAttr(com.google.android.material.R.attr.colorOnPrimarySurface)
                            buttonContinue.backgroundTintList =
                                ColorStateList.valueOf(buttonColor)
                            signInViewModel.processingEvent(
                                SignInIntent.ChangingReadinessButton(
                                    true
                                )
                            )
                        } else {
                            buttonColor =
                                requireContext().getColorFromAttr(com.google.android.material.R.attr.colorOnSecondary)
                            buttonContinue.backgroundTintList =
                                ColorStateList.valueOf(buttonColor)
                            signInViewModel.processingEvent(
                                SignInIntent.ChangingReadinessButton(
                                    false
                                )
                            )
                        }
                    }
                }
            }
        }

        buttonContinue.setOnClickListener {
            if (signInViewModel.viewState.value.buttonReadiness) {
                signInViewModel.processingEvent(SignInIntent.ContinueButtonClick)
            }
        }


    }

    private fun TextView.setHaveAccountText() {
        val haveAccountColor =
            requireActivity().getColorFromAttr(com.google.android.material.R.attr.colorSecondaryVariant)
        val haveAccountText = SpannableStringBuilder()
            .append(context.getString(com.dashkevich.ui.R.string.have_account))
            .append(" ")
            .color(haveAccountColor) { append(context.getString(com.dashkevich.ui.R.string.sign_up)) }
        text = haveAccountText
    }

    override fun onDestroyView() {
        super.onDestroyView()
        signInViewModel.processingEvent(SignInIntent.LeaveScreen)
    }

}