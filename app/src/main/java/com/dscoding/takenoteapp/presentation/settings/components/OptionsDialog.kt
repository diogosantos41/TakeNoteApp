package com.dscoding.takenoteapp.presentation.settings.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.common.TestTags
import com.dscoding.takenoteapp.presentation.common.DefaultRadioButton
import com.dscoding.takenoteapp.utils.Theme

@Composable
fun OptionsDialog(
    title: String,
    options: List<String>,
    selected: String,
    visible: Boolean = true,
    onOptionSelected: (Int) -> Unit,
    dismissDialog: () -> Unit,

) {
    val padding = dimensionResource(R.dimen.settings_dialog_margin_padding)

    if (visible) {
        AlertDialog(
            onDismissRequest = {
                dismissDialog()
            },
            title = {
                Column {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
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
                            onSelect = { onOptionSelected(index) },
                            testTag = if (option == Theme.DarkYellow.stringResource.asString()) TestTags.THEME_YELLOW_RADIO_BUTTON else ""
                        )
                        if (index != options.size - 1) {
                            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.settings_dialog_margin_between_options)))
                        }
                    }
                }
                Row(
                    modifier = Modifier.padding(all = padding)
                ) {
                    TextButton(onClick = dismissDialog) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(id = R.string.generic_cancel),
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            },
            backgroundColor = MaterialTheme.colorScheme.surface
        )
    }
}