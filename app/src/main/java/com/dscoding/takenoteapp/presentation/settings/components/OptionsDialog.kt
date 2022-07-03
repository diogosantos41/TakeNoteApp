package com.dscoding.takenoteapp.presentation.settings.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.presentation.list_notes.components.DefaultRadioButton
import com.dscoding.takenoteapp.ui.theme.ThemeManager

@Composable
fun OptionsDialog(
    title: String,
    options: List<String>,
    selected: String,
    onOptionSelected: (Int) -> Unit,
    dismissDialog: () -> Unit
) {
    val padding = dimensionResource(R.dimen.settings_dialog_margin_padding)

    AlertDialog(
        onDismissRequest = {
            dismissDialog()
        },
        title = {
            Text(text = title)
        },
        buttons = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding, padding, padding, 0.dp)
            ) {
                itemsIndexed(options) { index, option ->
                    DefaultRadioButton(
                        text = option,
                        selected = option == selected,
                        onSelect = { onOptionSelected(index) })
                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.settings_dialog_margin_between_options)))
                }
            }
            Row(
                modifier = Modifier.padding(all = padding),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = ThemeManager.colors.secondaryButtonBackground,
                        contentColor = ThemeManager.colors.secondaryButtonTextColor
                    ),
                    onClick = { dismissDialog() }
                ) {
                    Text(stringResource(id = R.string.generic_dismiss))
                }
            }
        }
    )
}