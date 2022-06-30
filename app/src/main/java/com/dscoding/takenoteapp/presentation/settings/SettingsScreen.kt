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
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
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
                        text = stringResource(id = R.string.settings_title), color = Color.White
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
                    SettingsHeader(text = stringResource(id = R.string.settings_header_user_interface))
                    Spacer(modifier = Modifier.height(betweenFieldsMargin))
                    SettingsField(
                        stringResource(R.string.settings_theme),
                        state.selectedTheme.asString(),
                        onClick = { viewModel.onEvent(SettingsEvent.ShowThemeOptionsDialog(true)) })
                    Spacer(modifier = Modifier.height(betweenFieldsMargin))
                    SwitchField(
                        stringResource(id = R.string.settings_show_greeting),
                        showGreetingState.value.asString(),
                        showGreetingState.isActive,
                        onSelect = {
                            viewModel.onEvent(SettingsEvent.ChangeShowGreetingState)
                        })
                }
                Divider()
                Column(settingsColumnModifier) {
                    Spacer(modifier = Modifier.height(headerTopMargin))
                    SettingsHeader(stringResource(id = R.string.settings_header_support))
                    Spacer(modifier = Modifier.height(betweenFieldsMargin))
                    SettingsField(
                        stringResource(id = R.string.settings_rate_app_title),
                        stringResource(id = R.string.settings_rate_app_message),
                        onClick = { })
                    Spacer(modifier = Modifier.height(betweenFieldsMargin))
                    SettingsField(
                        stringResource(id = R.string.settings_share_app_title),
                        stringResource(id = R.string.settings_share_app_message),
                        onClick = { })
                }
            }
            if (state.showThemeOptionsDialog) {
                OptionsDialog(
                    title = stringResource(id = R.string.settings_theme_dialog_title),
                    options = stringArrayResource(R.array.settings_theme_options_array).toList(),
                    selected = state.selectedTheme.asString(),
                    onOptionSelected = {
                        viewModel.onEvent(SettingsEvent.SelectThemeOption(it))
                    },
                    dismissDialog = { viewModel.onEvent(SettingsEvent.ShowThemeOptionsDialog(false)) }
                )
            }
        })
}

