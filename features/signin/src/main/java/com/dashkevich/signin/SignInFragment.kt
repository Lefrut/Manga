package com.dashkevich.signin

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.TextView
import androidx.core.text.color
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dashkevich.signin.databinding.FragmentSignInBinding
import com.dashkevich.ui.getColorFromAttr
import com.dashkevich.ui.setRegisterButtons


class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private lateinit var binding: FragmentSignInBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)
        binding.haveAccount.setHaveAccountText()
        setRegisterButtons(view)
        binding.haveAccount.setOnClickListener {
            findNavController().navigate(com.dashkevich.navigation.R.id.action_global_sign_up)
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

}