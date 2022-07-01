package com.dscoding.takenoteapp.ui.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

// primary
val Coral = Color(0xFFFF7F50)
val NavyBlue = Color(0xFF003B73)
val DarkerGrey = Color(0xFF404040)

val darkGrey = Color(0xFF808080)
val pink = Color(0xFFFFC0CB)
val red = Color(0xFFFF7F7F)
val blue = Color(0xFF87CEEB)
val green = Color(0xFFAED9B2)

/*primary = Purple500,
primaryVariant = Purple700,
secondary = Teal200

 Other default colors to override
background = Color.White,
surface = Color.White,
onPrimary = Color.White,
onSecondary = Color.Black,
onBackground = Color.Black,
onSurface = Color.Black,
*/

enum class TakeNoteTheme {
    DARK, LIGHT
}

@Stable
class CustomColors(
    backgroundColor: Color,
    buttonBackgroundColor: Color,
    buttonTextColor: Color,
    textColor: Color
) {
    var backgroundColor by mutableStateOf(backgroundColor)
        private set
    var buttonBackgroundColor by mutableStateOf(buttonBackgroundColor)
        private set
    var buttonTextColor by mutableStateOf(buttonTextColor)
        private set
    var textColor by mutableStateOf(textColor)
        private set

    fun update(colors: CustomColors) {
        this.backgroundColor = colors.backgroundColor
        this.buttonBackgroundColor = colors.buttonBackgroundColor
        this.buttonTextColor = colors.buttonTextColor
        this.textColor = colors.textColor
    }

    fun copy() = CustomColors(
        backgroundColor,
        buttonBackgroundColor,
        buttonTextColor,
        textColor
    )
}



