package com.dashkevich.signin

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.text.color
import androidx.fragment.app.Fragment
import com.dashkevich.signin.databinding.FragmentSignInBinding
import com.dashkevich.signin.model.CompanyButtonElement
import com.dashkevich.ui.databinding.IconButtonBinding
import com.dashkevich.ui.dp
import com.dashkevich.ui.getColorFromAttr


class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private lateinit var binding: FragmentSignInBinding
    private lateinit var iconBtnBinding: IconButtonBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)
        binding.haveAccount.setHaveAccountText()
        setRegisterButtons(view)
    }

    private fun TextView.setHaveAccountText() {
        val haveAccountColor =
            requireActivity().getColorFromAttr(com.google.android.material.R.attr.colorSecondaryVariant)
        val haveAccountText = SpannableStringBuilder()
            .append("Have account?")
            .append(" ")
            .color(haveAccountColor) { append("Sign in!") }
        text = haveAccountText
    }

    private fun setRegisterButtons(view: View){
        CompanyButtonElement.companies.forEach { company ->
            val buttonColor: Int = requireActivity().getColorFromAttr(company.buttonColor)
            val textColor: Int = requireActivity().getColorFromAttr(company.textColor)
            iconBtnBinding = IconButtonBinding.bind(view.findViewById(company.id))
            iconBtnBinding.apply {
                if (company.elevation != 0 && company.layoutBackground != null){
                    root.background = ContextCompat.getDrawable(requireContext(), com.dashkevich.ui.R.drawable.rectangle_25dp)
                    root.elevation = company.elevation.dp.toFloat()
                }
                companyIcon.setImageResource(company.icon)
                companyIcon.layoutParams.apply {
                    width = company.iconWidth.dp
                    height = company.iconHeight.dp
                }
                companyIcon.requestLayout()
                companyButton.apply {
                    setText(company.text)
                    backgroundTintList = ColorStateList.valueOf(buttonColor)
                    setTextColor(textColor)
                }
                companyButton.setOnClickListener {
                    Log.d("Debug", "Company button clicked")
                }
            }

        }
    }

}