package com.dscoding.takenoteapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.*

private val LightColors = CustomColors(
    backgroundColor = White,
    containerBackgroundColor = DirtWhite,
    iconColor = DarkerGrey,
    toolbarColor = DarkGrey,
    textColor = DarkGrey,
    mainColor = Coral
)

private val DarkColors = CustomColors(
    backgroundColor = DarkerGrey,
    containerBackgroundColor = DarkGrey,
    iconColor = White,
    toolbarColor = DarkGrey,
    textColor = White,
    mainColor = Coral
)

private val YellowColors = CustomColors(
    backgroundColor = BlackBlue,
    containerBackgroundColor = DarkGrey,
    iconColor = White,
    toolbarColor = DarkGrey,
    textColor = White,
    mainColor = Yellow
)

private val LocalColorsProvider = staticCompositionLocalOf {
    LightColors
}

@Composable
private fun LocalProvider(
    colors: CustomColors,
    content: @Composable () -> Unit
) {
    val colorPalette = remember {
        colors.copy()
    }

    colorPalette.update(colors)
    CompositionLocalProvider(LocalColorsProvider provides colorPalette, content = content)
}

enum class TakeNoteTheme {
    SYSTEM_DEFAULT, LIGHT, DARK, YELLOW
}

private val TakeNoteTheme.colors: Pair<Colors, CustomColors>
    get() = when (this) {
        TakeNoteTheme.DARK -> darkColors() to DarkColors
        TakeNoteTheme.LIGHT -> lightColors() to LightColors
        TakeNoteTheme.YELLOW -> darkColors() to YellowColors
        else -> lightColors() to LightColors
    }

object ThemeManager {
    val colors: CustomColors
        @Composable
        get() = LocalColorsProvider.current

    var takeNoteTheme by mutableStateOf(TakeNoteTheme.LIGHT)
}

@Composable
fun TakeNoteAppTheme(
    takeNoteTheme: TakeNoteTheme = ThemeManager.takeNoteTheme,
    content: @Composable () -> Unit
) {
    val (colorPalette, colors) = if (takeNoteTheme == TakeNoteTheme.SYSTEM_DEFAULT) {
        if (isSystemInDarkTheme()) {
            darkColors() to DarkColors
        } else {
            lightColors() to LightColors
        }
    } else {
        takeNoteTheme.colors
    }


    LocalProvider(colors = colors) {
        MaterialTheme(
            colors = colorPalette,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}