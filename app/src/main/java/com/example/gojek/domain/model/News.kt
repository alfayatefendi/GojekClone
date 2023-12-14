package com.example.gojek.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class News (
    @DrawableRes
    val imgSrc: Int,
    @StringRes
    val title: Int,
    @StringRes
    val description: Int
)