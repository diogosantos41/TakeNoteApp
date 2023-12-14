package com.dscoding.takenoteapp.presentation.common

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dscoding.takenoteapp.R

@Composable
fun EmptyListAlert(emptyMessage: String, showAnimations: Boolean = true) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .then(
                if (showAnimations) {
                    Modifier.animateContentSize()
                } else {
                    Modifier
                }
            )
            .padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(dimensionResource(id = R.dimen.empty_message_icon_size)),
                imageVector = Icons.Default.Info,
                contentDescription = stringResource(id = R.string.empty_list_content_description_icon),
                tint = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.margin)))
            Text(
                text = emptyMessage,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium

            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.empty_message_margin_bottom)))
        }
    }
}