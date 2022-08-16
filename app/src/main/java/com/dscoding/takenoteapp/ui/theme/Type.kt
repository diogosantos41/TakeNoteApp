package com.dscoding.takenoteapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.ui.theme.CustomTextStyle.body1
import com.dscoding.takenoteapp.ui.theme.CustomTextStyle.body2
import com.dscoding.takenoteapp.ui.theme.CustomTextStyle.button
import com.dscoding.takenoteapp.ui.theme.CustomTextStyle.caption
import com.dscoding.takenoteapp.ui.theme.CustomTextStyle.customFontFamily
import com.dscoding.takenoteapp.ui.theme.CustomTextStyle.h1
import com.dscoding.takenoteapp.ui.theme.CustomTextStyle.h2
import com.dscoding.takenoteapp.ui.theme.CustomTextStyle.h3
import com.dscoding.takenoteapp.ui.theme.CustomTextStyle.h4
import com.dscoding.takenoteapp.ui.theme.CustomTextStyle.h5
import com.dscoding.takenoteapp.ui.theme.CustomTextStyle.h6
import com.dscoding.takenoteapp.ui.theme.CustomTextStyle.overline
import com.dscoding.takenoteapp.ui.theme.CustomTextStyle.subtitle1
import com.dscoding.takenoteapp.ui.theme.CustomTextStyle.subtitle2

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = h1.withCustomFontFamily(),
    h2 = h2.withCustomFontFamily(),
    h3 = h3.withCustomFontFamily(),
    h4 = h4.withCustomFontFamily(),
    h5 = h5.withCustomFontFamily(),
    h6 = h6.withCustomFontFamily(),
    subtitle1 = subtitle1.withCustomFontFamily(),
    subtitle2 = subtitle2.withCustomFontFamily(),
    body1 = body1.withCustomFontFamily(),
    body2 = body2.withCustomFontFamily(),
    button = button.withCustomFontFamily(),
    caption = caption.withCustomFontFamily(),
    overline = overline.withCustomFontFamily(),
)

private fun TextStyle.withCustomFontFamily(): TextStyle {
    return this.copy(fontFamily = customFontFamily)
}

private object CustomTextStyle {
    val customFontFamily = FontFamily(
        Font(R.font.montserrat),
        Font(R.font.montserrat_bold, FontWeight.Bold),
        Font(R.font.montserrat_light, FontWeight.Light),
        Font(R.font.montserrat_medium, FontWeight.Medium)
    )
    val h1 = TextStyle(
        fontWeight = FontWeight.Light,
        fontSize = 96.sp,
        letterSpacing = (-1.5).sp
    )
    val h2 = TextStyle(
        fontWeight = FontWeight.Light,
        fontSize = 60.sp,
        letterSpacing = (-0.5).sp
    )
    val h3 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 48.sp,
        letterSpacing = 0.sp
    )
    val h4 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 34.sp,
        letterSpacing = 0.25.sp
    )
    val h5 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        letterSpacing = 0.sp
    )
    val h6 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        letterSpacing = 0.15.sp
    )
    val subtitle1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.15.sp
    )
    val subtitle2 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = 0.1.sp
    )
    val body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.5.sp
    )
    val body2 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.25.sp
    )
    val button = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = 1.25.sp
    )
    val caption = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 0.4.sp
    )
    val overline = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        letterSpacing = 1.5.sp
    )
}