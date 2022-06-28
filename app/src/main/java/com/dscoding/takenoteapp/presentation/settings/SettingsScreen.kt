package com.dscoding.takenoteapp.presentation.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.presentation.settings.components.SettingsHeader
import com.dscoding.takenoteapp.presentation.settings.components.SwitchField

@Composable
fun SettingsScreen(navController: NavController) {

    val generalMargin = dimensionResource(R.dimen.general_margin)
    val headerTopMargin = dimensionResource(R.dimen.settings_margin_header_top)
    val betweenFieldsMargin = dimensionResource(R.dimen.general_margin)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Settings", color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, "Back Arrow", tint = Color.White)
                    }
                },
                backgroundColor = Color.DarkGray,
                elevation = 0.dp
            )
        },
        content = { padding ->
            val settingsColumnModifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(
                    generalMargin,
                    generalMargin,
                    generalMargin,
                    generalMargin
                )
            Column(modifier = Modifier.padding(generalMargin, 0.dp)) {
                Column(settingsColumnModifier) {
                    Spacer(modifier = Modifier.height(headerTopMargin))
                    SettingsHeader(text = "UI")
                    Spacer(modifier = Modifier.height(betweenFieldsMargin))
                    SwitchField("Show Greeting", "Active", true, onSelect = { })
                    Spacer(modifier = Modifier.height(betweenFieldsMargin))
                    SwitchField("Name", "Diogo", false, onSelect = { })
                    Spacer(modifier = Modifier.height(betweenFieldsMargin))
                    SwitchField("Theme", "System", false, onSelect = { })
                    Spacer(modifier = Modifier.height(betweenFieldsMargin))
                    SwitchField("Grid layout notes", "2", false, onSelect = { })
                }
                Divider()
                Column(settingsColumnModifier) {
                    Spacer(modifier = Modifier.height(headerTopMargin))
                    SettingsHeader(text = "About")
                    Spacer(modifier = Modifier.height(betweenFieldsMargin))
                    SwitchField("Show Greeting", "Active", true, onSelect = { })
                    Spacer(modifier = Modifier.height(betweenFieldsMargin))
                    SwitchField("Name", "Diogo", false, onSelect = { })
                    Spacer(modifier = Modifier.height(betweenFieldsMargin))
                    SwitchField("Theme", "System", false, onSelect = { })
                    Spacer(modifier = Modifier.height(betweenFieldsMargin))
                    SwitchField("Grid layout notes", "2", false, onSelect = { })
                }
            }
        })
}

