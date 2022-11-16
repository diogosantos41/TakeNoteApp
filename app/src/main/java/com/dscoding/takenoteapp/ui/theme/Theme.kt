package com.dscoding.takenoteapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.dscoding.takenoteapp.utils.Theme

private val LightColors = lightColors(
    primary = Coral,
    secondary = White,
    background = White,
    surface = DirtWhite,
    onSurface = DarkGrey,
    onPrimary = DarkGrey
)

private val DarkColors = darkColors(
    primary = Coral,
    secondary = DarkerGrey,
    background = DarkerGrey,
    surface = DarkGrey,
    onSurface = White,
    onPrimary = White
)

private val DarkYellowColors = darkColors(
    primary = Yellow,
    secondary = BlackBlue,
    background = BlackBlue,
    surface = DarkGrey,
    onSurface = White,
    onPrimary = White
)

@Composable
fun getThemeColors(theme: Theme): Colors {
    return when (theme) {
        Theme.SystemDefault -> if (isSystemInDarkTheme()) DarkColors else LightColors
        Theme.Light -> LightColors
        Theme.Dark -> DarkColors
        Theme.DarkYellow -> DarkYellowColors
        Theme.Dynamic -> DarkYellowColors // TODO dynamic colors
    }
}

@Composable
fun TakeNoteAppTheme(
    theme: Theme = Theme.SystemDefault,
    content: @Composable () -> Unit
) {

    val colors = getThemeColors(theme = theme)

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}