package com.dscoding.takenoteapp.presentation.common

import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import com.dscoding.takenoteapp.ui.theme.DarkGrey
import com.dscoding.takenoteapp.ui.theme.White

@Composable
fun SnackbarHostController(
    snackbarHostState: SnackbarHostState
) {
    SnackbarHost(snackbarHostState) { data ->
        Snackbar(
            contentColor = White,
            backgroundColor = DarkGrey,
            snackbarData = data
        )
    }
}