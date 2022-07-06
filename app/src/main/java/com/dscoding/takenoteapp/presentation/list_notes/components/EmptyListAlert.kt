package com.dscoding.takenoteapp.presentation.list_notes.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.ui.theme.ThemeManager


@Composable
fun EmptyListAlert(emptyMessage: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(dimensionResource(id = R.dimen.empty_message_icon_size)),
                imageVector = Icons.Default.Info,
                contentDescription = "Info",
                tint = ThemeManager.colors.iconColor
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.margin)))
            Text(
                text = emptyMessage,
                style = MaterialTheme.typography.body1,
                color = ThemeManager.colors.textColor,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold

            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.empty_message_margin_bottom)))
        }
    }
}