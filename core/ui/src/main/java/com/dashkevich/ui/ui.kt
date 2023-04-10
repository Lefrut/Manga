package com.dashkevich.ui

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.dashkevich.ui.databinding.IconButtonBinding
import com.dashkevich.ui.model.CompanyButtonElement


val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Int.px: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()


@ColorInt
fun Context.getColorFromAttr(
    @AttrRes attrColor: Int,
    typedValue: TypedValue = TypedValue(),
    resolveRefs: Boolean = true
): Int {
    theme.resolveAttribute(attrColor, typedValue, resolveRefs)
    return typedValue.data
}

fun setRegisterButtons(
    view: View
) {
    val context = view.context
    CompanyButtonElement.companies.forEach { company ->
        val buttonColor: Int = context.getColorFromAttr(company.buttonColor)
        val textColor: Int = context.getColorFromAttr(company.textColor)
        val binding = IconButtonBinding.bind(view.findViewById(company.id))
        binding.apply {
            if (company.elevation != 0 && company.layoutBackground != null) {
                root.background = ContextCompat.getDrawable(context, R.drawable.rectangle_25dp)
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
                company.onClick()
            }
        }
    }

}
