package com.dscoding.takenoteapp.utils

import androidx.compose.runtime.Composable
import com.dscoding.takenoteapp.common.StringResource

@Composable
fun geThemesTextList(): List<String> {
    val list = mutableListOf(
        Theme.SystemDefault.stringResource.asString(),
        Theme.Light.stringResource.asString(),
        Theme.Dark.stringResource.asString(),
        Theme.DarkYellow.stringResource.asString()
    )
    if (supportDynamicColors()) {
        list.add(Theme.Dynamic.stringResource.asString())
    }
    return list
}

fun getAppTheme(id: Int): Theme {
    val map = Theme.values().associateBy(Theme::id)
    map[id].let {
        return it!!
    }
}

fun getThemeText(id: Int): StringResource {
    return getAppTheme(id).stringResource
}