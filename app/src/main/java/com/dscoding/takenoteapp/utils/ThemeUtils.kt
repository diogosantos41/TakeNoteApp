package com.dscoding.takenoteapp.utils

import androidx.compose.runtime.Composable
import com.dscoding.takenoteapp.common.UiText

@Composable
fun geThemesTextList(): List<String> {
    val list = mutableListOf(
        Theme.SystemDefault.uiText.asString(),
        Theme.Light.uiText.asString(),
        Theme.Dark.uiText.asString(),
        Theme.DarkYellow.uiText.asString()
    )
    if (supportDynamicColors()) {
        list.add(Theme.Dynamic.uiText.asString())
    }
    return list
}

fun getAppTheme(id: Int): Theme {
    val map = Theme.values().associateBy(Theme::id)
    map[id].let {
        return it!!
    }
}

fun getThemeText(id: Int): UiText {
    return getAppTheme(id).uiText
}