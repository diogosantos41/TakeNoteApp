package com.dscoding.takenoteapp.utils

import androidx.compose.runtime.Composable

@Composable
fun geThemesTextList(): List<String> {
    return listOf(
        TakeNoteTheme.SYSTEM_DEFAULT.uiText.asString(),
        TakeNoteTheme.LIGHT.uiText.asString(),
        TakeNoteTheme.DARK.uiText.asString(),
        TakeNoteTheme.DARK_YELLOW.uiText.asString()
    )
}

fun getTheme(id: Int): TakeNoteTheme {
    val map = TakeNoteTheme.values().associateBy(TakeNoteTheme::id)
    map[id].let {
        return it!!
    }
}

fun getThemeText(id: Int): UiText {
    return getTheme(id).uiText
}