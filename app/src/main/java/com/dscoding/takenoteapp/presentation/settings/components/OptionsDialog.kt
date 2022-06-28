package com.dscoding.takenoteapp.presentation.settings.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dscoding.takenoteapp.presentation.list_notes.components.DefaultRadioButton
import com.dscoding.takenoteapp.presentation.settings.SettingsEvent
import com.dscoding.takenoteapp.presentation.settings.SettingsViewModel

@Composable
fun OptionsDialog(viewModel: SettingsViewModel, title: String, options: List<String>) {

    var text by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = {
            viewModel.onEvent(SettingsEvent.ShowDialog(false))
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
                items(options) { option ->
                    DefaultRadioButton(text = option, selected = false, onSelect = { })
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
            Row(
                modifier = Modifier.padding(all = 20.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { viewModel.onEvent(SettingsEvent.ShowDialog(false)) }
                ) {
                    Text("Dismiss")
                }
            }
        }
    )
}