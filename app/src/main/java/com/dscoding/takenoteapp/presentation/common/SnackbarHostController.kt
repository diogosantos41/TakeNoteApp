package com.dscoding.takenoteapp.presentation.common

import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import com.dscoding.takenoteapp.ui.theme.Grey
import com.dscoding.takenoteapp.ui.theme.ThemeManager
import com.dscoding.takenoteapp.ui.theme.White

@Composable
fun SnackbarHostController(
    snackbarHostState: SnackbarHostState
) {
    SnackbarHost(snackbarHostState) { data ->
        Snackbar(
            actionColor = ThemeManager.colors.mainColor,
            contentColor = White,
            backgroundColor = Grey,
            snackbarData = data
        )
    }
}