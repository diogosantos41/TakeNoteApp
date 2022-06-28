package com.dscoding.takenoteapp.presentation.settings.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SwitchField(
    title: String,
    value: String,
    active: Boolean,
    onSelect: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.body1,
                color = Color.Black
            )
            Text(
                text = value,
                style = MaterialTheme.typography.body2,
                color = Color.Gray
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Switch(
            checked = active,
            onCheckedChange = onSelect,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Blue,
                uncheckedThumbColor = Color.Gray,
                checkedTrackColor = Color.Gray,
                uncheckedTrackColor = Color.LightGray,
            )
        )
    }
}