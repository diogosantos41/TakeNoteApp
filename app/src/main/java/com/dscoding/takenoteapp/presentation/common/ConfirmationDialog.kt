package com.dscoding.takenoteapp.presentation.common

import androidx.compose.material.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.dscoding.takenoteapp.R

@Composable
fun ConfirmationDialog(
    message: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onConfirm)
            {
                Text(
                    text = stringResource(id = R.string.generic_yes),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss)
            {
                Text(
                    text = stringResource(id = R.string.generic_cancel),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        },
        title = {
            Text(
                text = message,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        backgroundColor = MaterialTheme.colorScheme.surface
    )
}