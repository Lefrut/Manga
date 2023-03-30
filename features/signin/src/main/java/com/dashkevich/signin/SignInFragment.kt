package com.dashkevich.signin

import android.content.Context
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import com.dashkevich.signin.databinding.FragmentSignInBinding
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.core.text.color


class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private lateinit var binding: FragmentSignInBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)
        binding.haveAccount.setHaveAccountText()
    }

    @ColorInt
    fun Context.getColorFromAttr(
        @AttrRes attrColor: Int,
        typedValue: TypedValue = TypedValue(),
        resolveRefs: Boolean = true
    ): Int {
        theme.resolveAttribute(attrColor, typedValue, resolveRefs)
        return typedValue.data
    }

    private fun TextView.setHaveAccountText(){
        val haveAccountColor =
            requireActivity().getColorFromAttr(com.google.android.material.R.attr.colorSecondaryVariant)
        val haveAccountText = SpannableStringBuilder()
            .append("Have account?")
            .append(" ")
            .color(haveAccountColor) { append("Sign in!") }
        text = haveAccountText
    }

}