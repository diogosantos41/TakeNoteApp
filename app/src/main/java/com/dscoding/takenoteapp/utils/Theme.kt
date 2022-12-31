package com.dscoding.takenoteapp.utils

import androidx.compose.runtime.Composable
import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.common.StringResource

enum class Theme(val id: Int, val stringResource: StringResource) {
    SystemDefault(0, StringResource(resId = R.string.settings_theme_option_system_default)),
    Light(1, StringResource(resId = R.string.settings_theme_option_light)),
    Dark(2, StringResource(resId = R.string.settings_theme_option_dark)),
    DarkYellow(3, StringResource(resId = R.string.settings_theme_option_dark_yellow)),
    Dynamic(4, StringResource(resId = R.string.settings_theme_option_dynamic))
}

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