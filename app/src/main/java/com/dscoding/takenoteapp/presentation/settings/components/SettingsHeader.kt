package com.dscoding.takenoteapp.presentation.settings.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.dscoding.takenoteapp.ui.theme.ThemeManager

@Composable
fun SettingsHeader(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.body2,
        color = ThemeManager.colors.mainColor,
        fontWeight = FontWeight.Bold
    )
}