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
import androidx.compose.ui.unit.dp
import com.dscoding.takenoteapp.presentation.list_notes.components.DefaultRadioButton

@Composable
fun OptionsDialog(
    title: String,
    options: List<String>,
    selected: String,
    onOptionSelected: (Int) -> Unit,
    dismissDialog: () -> Unit
) {
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
                    .padding(all = 20.dp)
            ) {
                itemsIndexed(options) { index, option ->
                    DefaultRadioButton(
                        text = option,
                        selected = option == selected,
                        onSelect = { onOptionSelected(index) })
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
            Row(
                modifier = Modifier.padding(all = 20.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(),
                    onClick = { dismissDialog() }
                ) {
                    Text("Dismiss")
                }
            }
        }
    )
}