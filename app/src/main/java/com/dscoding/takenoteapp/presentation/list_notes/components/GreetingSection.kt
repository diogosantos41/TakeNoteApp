package com.dscoding.takenoteapp.presentation.list_notes.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dscoding.takenoteapp.R

@Composable
fun GreetingSection(
    name: String = "Diogo"
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                0.dp,
                dimensionResource(R.dimen.margin_vertical_greeting_section),
                0.dp,
                0.dp
            )
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Hey there $name!",
                style = MaterialTheme.typography.h6
            )
            Text(
                text = "We wish you have a good day!",
                style = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.margin_vertical_greeting_section)))
            Divider()
        }
    }
}