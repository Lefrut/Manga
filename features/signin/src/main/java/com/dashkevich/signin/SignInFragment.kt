package com.dashkevich.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.dashkevich.signin.databinding.FragmentSignInBinding

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    lateinit var binding: FragmentSignInBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)
    }

}