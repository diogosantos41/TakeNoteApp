package com.dscoding.takenoteapp.ui.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

val Coral = Color(0xFFFF7F50)
val Yellow = Color(0xFFFCD535)

val White = Color(0xFFFFFFFF)
val DirtWhite = Color(0xFFFAFAFA)
val Grey = Color(0xFF808080)
val DarkGrey = Color(0xFF303030)
val DarkerGrey = Color(0xFF151515)
val BlackBlue = Color(0xFF0b0E11)

val LightPink = Color(0xFFf8C6FF)
val LightRed = Color(0xFFFFC6C6)
val LightBlue = Color(0xFFC6D9FF)
val LightGreen = Color(0xFFCDFFC6)
val LightYellow = Color(0xFFFFF4C6)

@Stable
class CustomColors(
    backgroundColor: Color,
    containerBackgroundColor: Color,
    iconColor: Color,
    toolbarColor: Color,
    secondaryButtonBackgroundColor: Color,
    secondaryButtonTextColor: Color,
    textColor: Color,
    mainColor: Color
) {
    var backgroundColor by mutableStateOf(backgroundColor)
        private set
    var containerBackgroundColor by mutableStateOf(containerBackgroundColor)
        private set
    var iconColor by mutableStateOf(iconColor)
        private set
    var toolbarColor by mutableStateOf(toolbarColor)
        private set
    var secondaryButtonBackground by mutableStateOf(secondaryButtonBackgroundColor)
        private set
    var secondaryButtonTextColor by mutableStateOf(secondaryButtonTextColor)
        private set
    var textColor by mutableStateOf(textColor)
        private set
    var mainColor by mutableStateOf(mainColor)
        private set

    fun update(colors: CustomColors) {
        this.backgroundColor = colors.backgroundColor
        this.containerBackgroundColor = colors.containerBackgroundColor
        this.iconColor = colors.iconColor
        this.toolbarColor = colors.toolbarColor
        this.secondaryButtonBackground = colors.secondaryButtonBackground
        this.secondaryButtonTextColor = colors.secondaryButtonTextColor
        this.textColor = colors.textColor
        this.mainColor = colors.mainColor

    }

    fun copy() = CustomColors(
        backgroundColor,
        containerBackgroundColor,
        iconColor,
        toolbarColor,
        secondaryButtonBackground,
        secondaryButtonTextColor,
        textColor,
        mainColor
    )
}



