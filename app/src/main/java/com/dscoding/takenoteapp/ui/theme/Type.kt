package com.dscoding.takenoteapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.dscoding.takenoteapp.R

val customFontFamily = FontFamily(
    Font(R.font.montserrat),
    Font(R.font.montserrat_bold, FontWeight.Bold),
    Font(R.font.montserrat_light, FontWeight.Light),
    Font(R.font.montserrat_medium, FontWeight.Medium)
)

val Typography = Typography(
    defaultFontFamily = customFontFamily
)

