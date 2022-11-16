package com.dscoding.takenoteapp.utils

import android.os.Build
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
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        list.add(Theme.Dynamic.uiText.asString())
    }
    return list
}

fun getTheme(id: Int): Theme {
    val map = Theme.values().associateBy(Theme::id)
    map[id].let {
        return it!!
    }
}

fun getThemeText(id: Int): UiText {
    return getTheme(id).uiText
}