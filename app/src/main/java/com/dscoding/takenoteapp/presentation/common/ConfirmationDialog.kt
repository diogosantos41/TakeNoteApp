package com.dscoding.takenoteapp.presentation.common

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.dscoding.takenoteapp.R

@Composable
fun ConfirmationDialog(
    visible: Boolean = true,
    message: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    if(visible) {
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(onClick = onConfirm)
                {
                    Text(
                        text = stringResource(id = R.string.generic_yes),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss)
                {
                    Text(
                        text = stringResource(id = R.string.generic_cancel),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )
                }
            },
            title = {
                Text(
                    text = message,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            backgroundColor = MaterialTheme.colorScheme.surface
        )
    }
}