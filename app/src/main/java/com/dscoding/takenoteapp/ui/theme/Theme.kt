package com.dscoding.takenoteapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

/*private val LightColorPalette = lightColors(
   primary = Coral,
   primaryVariant = Coral,
   secondary = NavyBlue
)

private val DarkColorPalette = darkColors(
   primary = DarkerGrey,
   primaryVariant = DarkerGrey,
   secondary = Coral

   Other default colors to override
   background = Color.White,
   surface = Color.White,
   onPrimary = Color.White,
   onSecondary = Color.Black,
   onBackground = Color.Black,
   onSurface = Color.Black,

)
 */

private val LightColors = CustomColors(
    backgroundColor = Color.White,
    buttonBackgroundColor = Coral,
    buttonTextColor = Color.White,
    textColor = Color.White
)

private val DarkColors = CustomColors(
    backgroundColor = DarkerGrey,
    buttonBackgroundColor = NavyBlue,
    buttonTextColor = Color.White,
    textColor = Color.White
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
    SYSTEM_DEFAULT, LIGHT, DARK
}

private val TakeNoteTheme.colors: Pair<Colors, CustomColors>
    get() = when (this) {
        TakeNoteTheme.DARK -> darkColors() to DarkColors
        TakeNoteTheme.LIGHT -> lightColors() to LightColors
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