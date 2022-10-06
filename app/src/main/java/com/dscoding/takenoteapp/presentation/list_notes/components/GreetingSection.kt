package com.dscoding.takenoteapp.presentation.list_notes.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.ui.theme.ThemeManager

@Composable
fun GreetingSection() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                0.dp,
                dimensionResource(R.dimen.notes_margin_vertical_greeting_section),
                0.dp,
                0.dp
            )
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.notes_greeting_title),
                style = MaterialTheme.typography.h6,
                color = ThemeManager.colors.textColor
            )
            Text(
                text = stringResource(id = R.string.notes_greeting_message),
                style = MaterialTheme.typography.body1,
                color = ThemeManager.colors.mainColor,
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.notes_margin_vertical_greeting_section)))
            Divider()
        }
    }
}