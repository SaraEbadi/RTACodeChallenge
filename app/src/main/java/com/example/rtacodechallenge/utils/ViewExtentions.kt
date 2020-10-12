package com.example.rtacodechallenge.utils

import android.content.res.ColorStateList
import android.widget.Button
import androidx.core.content.ContextCompat
import com.example.rtacodechallenge.R

fun Button.changeButtonAppearance(btnColor: Int, btnText: Int) {
    backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, btnColor))
    text = context.getString(btnText)
}

fun Button.toggleSelection(isAdd: Boolean) {
    changeButtonAppearance(R.color.colorPrimary, R.string.add)
    if (isAdd)
        changeButtonAppearance(R.color.colorDarkGray, R.string.added)
}


