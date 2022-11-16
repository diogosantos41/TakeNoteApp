package com.dscoding.takenoteapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.dscoding.takenoteapp.utils.Theme

private val LightColors = lightColorScheme(
    primary = Coral,
    onPrimary = DarkGrey,
    secondary = White,
    background = White,
    onBackground = DarkGrey,
    surface = DirtWhite,
    onSurface = DarkGrey
)

private val DarkColors = darkColorScheme(
    primary = Coral,
    onPrimary = White,
    secondary = DarkerGrey,
    background = DarkerGrey,
    onBackground = White,
    surface = DarkGrey,
    onSurface = White,

)

private val DarkYellowColors = darkColorScheme(
    primary = Yellow,
    onPrimary = White,
    secondary = BlackBlue,
    background = BlackBlue,
    onBackground = White,
    surface = DarkGrey,
    onSurface = White,

)

@Composable
fun getThemeColors(theme: Theme): ColorScheme {
    val context = LocalContext.current
    return when (theme) {
        Theme.SystemDefault -> if (isSystemInDarkTheme()) DarkColors else LightColors
        Theme.Light -> LightColors
        Theme.Dark -> DarkColors
        Theme.DarkYellow -> DarkYellowColors
        Theme.Dynamic -> if (isSystemInDarkTheme()) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    }
}

@Composable
fun TakeNoteAppTheme(
    theme: Theme = Theme.SystemDefault,
    content: @Composable () -> Unit
) {

    val colors = getThemeColors(theme = theme)

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}