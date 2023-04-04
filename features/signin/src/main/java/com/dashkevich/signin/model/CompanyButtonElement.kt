package com.dashkevich.signin.model

import androidx.annotation.AttrRes
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import com.dashkevich.signin.R

class CompanyButtonElement(
    @IdRes
    val id: Int,
    @DrawableRes
    val icon: Int,
    val iconHeight: Int,
    val iconWidth: Int,
    @StringRes
    val text: Int,
    @AttrRes
    val buttonColor: Int,
    @AttrRes
    val textColor: Int,
    val elevation: Int = 0,
    @DrawableRes
    val layoutBackground: Int? = null
){

    companion object{
        val companies = listOf<CompanyButtonElement>(
            CompanyButtonElement(
                id = R.id.meta_button,
                icon = com.dashkevich.ui.R.drawable.meta_4x,
                iconHeight = 27,
                iconWidth = 29,
                text = com.dashkevich.ui.R.string.continue_with_meta,
                buttonColor = com.dashkevich.ui.R.attr.MetaColor,
                textColor = com.google.android.material.R.attr.colorOnPrimary
            ),
            CompanyButtonElement(
                id = R.id.google_button,
                icon = com.dashkevich.ui.R.drawable.google_4x,
                iconHeight = 29,
                iconWidth = 28,
                text = com.dashkevich.ui.R.string.continue_with_google,
                buttonColor = com.google.android.material.R.attr.colorOnPrimary,
                textColor = com.google.android.material.R.attr.colorOnPrimarySurface,
                elevation = 7,
                layoutBackground = com.dashkevich.ui.R.drawable.rectangle_25dp
            )
        )
    }
}