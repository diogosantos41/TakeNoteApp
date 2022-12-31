package com.dscoding.takenoteapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.common.StringResource
import com.dscoding.takenoteapp.ui.theme.montserratFontFamily

enum class Font(val id: Int, val stringResource: StringResource, val fontFamily: FontFamily) {
    Montserrat(0, StringResource(resId = R.string.settings_font_option_montserrat_default), montserratFontFamily),
    Roboto(1, StringResource(resId = R.string.settings_font_option_roboto), FontFamily.Default),
    Serif(2, StringResource(resId = R.string.settings_font_option_serif), FontFamily.Serif),
    Monospace(3, StringResource(resId = R.string.settings_font_option_monospace), FontFamily.Monospace)
}

@Composable
fun geFontsTextList(): List<String> {
    return mutableListOf(
        Font.Montserrat.stringResource.asString(),
        Font.Roboto.stringResource.asString(),
        Font.Serif.stringResource.asString(),
        Font.Monospace.stringResource.asString()
    )
}

fun getAppFont(id: Int): Font {
    val map = Font.values().associateBy(Font::id)
    map[id].let {
        return it!!
    }
}

fun getFontText(id: Int): StringResource {
    return getAppFont(id).stringResource
}