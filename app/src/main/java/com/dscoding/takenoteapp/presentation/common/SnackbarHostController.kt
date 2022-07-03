package com.dscoding.takenoteapp.presentation.common

import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.dscoding.takenoteapp.ui.theme.Grey
import com.dscoding.takenoteapp.ui.theme.ThemeManager

@Composable
fun SnackbarHostController(
    snackbarHostState: SnackbarHostState
) {
    SnackbarHost(snackbarHostState) { data ->
        Snackbar(
            actionColor = ThemeManager.colors.mainColor,
            contentColor = ThemeManager.colors.textColor,
            backgroundColor = Grey,
            snackbarData = data
        )
    }
}