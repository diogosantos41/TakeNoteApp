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

private val CustomLightColors = CustomColors(
    backgroundColor = Color.White,
    buttonBackgroundColor = Coral,
    buttonTextColor = Color.White,
    textColor = Color.White
)

private val CustomDarkColors = CustomColors(
    backgroundColor = DarkerGrey,
    buttonBackgroundColor = NavyBlue,
    buttonTextColor = Color.White,
    textColor = Color.White
)

private val LocalColorsProvider = staticCompositionLocalOf {
    CustomLightColors
}

@Composable
private fun CustomLocalProvider(
    colors: CustomColors,
    content: @Composable () -> Unit
) {
    val colorPalette = remember {
        colors.copy()
    }

    colorPalette.update(colors)
    CompositionLocalProvider(LocalColorsProvider provides colorPalette, content = content)
}

private val CustomTheme.colors: Pair<Colors, CustomColors>
    get() = when (this) {
        CustomTheme.DARK -> darkColors() to CustomDarkColors
        CustomTheme.LIGHT -> lightColors() to CustomLightColors
    }

object CustomThemeManager {
    val colors: CustomColors
        @Composable
        get() = LocalColorsProvider.current

    var customTheme by mutableStateOf(CustomTheme.LIGHT)

    fun isSystemInDarkTheme(): Boolean {
        return customTheme == CustomTheme.DARK
    }
}

@Composable
fun TakeNoteAppTheme(
    customTheme: CustomTheme = CustomThemeManager.customTheme,
    content: @Composable () -> Unit
) {
    val (colorPalette, lcColor) = customTheme.colors

    CustomLocalProvider(colors = lcColor) {
        MaterialTheme(
            colors = colorPalette,
            typography = Typography,
            shapes = Shapes,
            content = content
        )

    }
}