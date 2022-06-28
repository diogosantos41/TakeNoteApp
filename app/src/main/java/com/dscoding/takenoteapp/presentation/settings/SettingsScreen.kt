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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.presentation.settings.components.OptionsDialog
import com.dscoding.takenoteapp.presentation.settings.components.SettingsField
import com.dscoding.takenoteapp.presentation.settings.components.SettingsHeader
import com.dscoding.takenoteapp.presentation.settings.components.SwitchField

@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel()
) {

    val showGreetingState = viewModel.showGreetingFieldState.value
    val state = viewModel.state.value

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
            if (state.showDialog) {
                OptionsDialog(
                    viewModel,
                    "Choose Theme",
                    listOf("Option 1", "Option 2", "Option3")
                )
            }
            Column(modifier = Modifier.padding(generalMargin, 0.dp)) {
                Column(settingsColumnModifier) {

                    Spacer(modifier = Modifier.height(headerTopMargin))
                    SettingsHeader(text = "User Interface")
                    Spacer(modifier = Modifier.height(betweenFieldsMargin))
                    SettingsField(
                        "Theme",
                        "System default",
                        onClick = { viewModel.onEvent(SettingsEvent.ShowDialog(true)) })
                    Spacer(modifier = Modifier.height(betweenFieldsMargin))
                    SwitchField(
                        "Show greeting",
                        showGreetingState.value,
                        showGreetingState.isActive,
                        onSelect = {
                            viewModel.onEvent(SettingsEvent.ChangeShowGreetingState)
                        })
                    Spacer(modifier = Modifier.height(betweenFieldsMargin))
                    SettingsField(
                        "Grid layout rows",
                        "2",
                        onClick = { viewModel.onEvent(SettingsEvent.ShowDialog(true)) })
                }
                Divider()
                Column(settingsColumnModifier) {
                    Spacer(modifier = Modifier.height(headerTopMargin))
                    SettingsHeader(text = "Support")
                    Spacer(modifier = Modifier.height(betweenFieldsMargin))
                    SettingsField(
                        "Rate the application",
                        "Reviews make us very happy. Thank you.",
                        onClick = { })
                    Spacer(modifier = Modifier.height(betweenFieldsMargin))
                    SettingsField(
                        "Share app",
                        "Feel free to share the app with your friends.",
                        onClick = { })
                }
            }
        })
}

