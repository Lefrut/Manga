package com.dashkevich.signin

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.View
import com.dashkevich.signin.databinding.FragmentSignInBinding
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt


class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private lateinit var binding: FragmentSignInBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)
        binding.haveAccount.setText()
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

    private fun TextView.setText(){
        val haveAccountString = "Have account?" + " " + "Sign in!"
        val spannable = SpannableString(haveAccountString)
        val color = context.getColorFromAttr(com.google.android.material.R.attr.colorSecondary)
        spannable.setSpan(
            ForegroundColorSpan(color),
            haveAccountString.length,
            (haveAccountString + "Sign in!").length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }


}