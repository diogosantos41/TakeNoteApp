package com.dscoding.takenoteapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.dscoding.takenoteapp.utils.Font
import com.dscoding.takenoteapp.utils.Theme
import com.dscoding.takenoteapp.utils.supportDynamicColors

private val LightColors = lightColorScheme(
    primary = Coral,
    onPrimary = DarkGrey,
    secondary = White,
    background = White,
    onBackground = DarkGrey,
    surface = DirtyWhite,
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

private val PinkBlueColors = lightColorScheme(
    primary = Pink,
    onPrimary = DarkGrey,
    secondary = WhiteBlue,
    background = WhiteBlue,
    onBackground = DarkGrey,
    surface = DirtyWhite,
    onSurface = DarkGrey
)

@Composable
fun getThemeColors(theme: Theme): ColorScheme {
    val context = LocalContext.current
    return when (theme) {
        Theme.SystemDefault -> if (isSystemInDarkTheme()) DarkColors else LightColors
        Theme.Light -> LightColors
        Theme.Dark -> DarkColors
        Theme.DarkYellow -> DarkYellowColors
        Theme.PinkBlue -> PinkBlueColors
        Theme.Dynamic -> if (supportDynamicColors()) {
            if (isSystemInDarkTheme()) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        } else {
            if (isSystemInDarkTheme()) DarkColors else LightColors
        }
    }
}

@Composable
fun TakeNoteAppTheme(
    theme: Theme = Theme.SystemDefault,
    font: Font = Font.Montserrat,
    content: @Composable () -> Unit
) {

    val colors = getThemeColors(theme = theme)
    val typography = Typography(fontFamily = font.fontFamily)

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = Shapes,
        content = content
    )
}