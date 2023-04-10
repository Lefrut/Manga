package com.dashkevich.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.dashkevich.signup.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private lateinit var binding: FragmentSignUpBinding

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


    }
}