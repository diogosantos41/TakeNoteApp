package com.dscoding.takenoteapp.presentation.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dscoding.takenoteapp.BuildConfig
import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.common.TestTags
import com.dscoding.takenoteapp.presentation.settings.components.OptionsDialog
import com.dscoding.takenoteapp.presentation.settings.components.SettingsField
import com.dscoding.takenoteapp.presentation.settings.components.SettingsHeader
import com.dscoding.takenoteapp.presentation.settings.components.SwitchField
import com.dscoding.takenoteapp.ui.theme.DarkGrey
import com.dscoding.takenoteapp.ui.theme.DividerGrey
import com.dscoding.takenoteapp.ui.theme.White
import com.dscoding.takenoteapp.utils.extensions.launchShareAppIntent
import com.dscoding.takenoteapp.utils.extensions.openGooglePlayAppPage
import com.dscoding.takenoteapp.utils.extensions.openPrivacyPolicyPage
import com.dscoding.takenoteapp.utils.geFontsTextList
import com.dscoding.takenoteapp.utils.geThemesTextList

@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val showGreetingState = viewModel.showGreetingFieldState.value
    val twentyFourHourClockState = viewModel.twentyFourHourClockFieldState.value
    val state = viewModel.state.value

    val generalMargin = dimensionResource(R.dimen.margin)
    val headerTopMargin = dimensionResource(R.dimen.settings_margin_header_top)
    val betweenFieldsMargin = dimensionResource(R.dimen.margin)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.settings_title),
                        color = White,
                        style = MaterialTheme.typography.headlineSmall
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.settings_content_description_back),
                            tint = White
                        )
                    }
                },
                backgroundColor = DarkGrey,
                elevation = 0.dp
            )
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(padding)
            ) {
                val settingsColumnModifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        generalMargin,
                        generalMargin,
                        generalMargin,
                        generalMargin
                    )
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(generalMargin, 0.dp)
                ) {
                    Column(settingsColumnModifier) {
                        Spacer(modifier = Modifier.height(headerTopMargin))
                        SettingsHeader(text = stringResource(id = R.string.settings_header_user_interface))
                        Spacer(modifier = Modifier.height(betweenFieldsMargin))
                        SettingsField(
                            stringResource(R.string.settings_theme),
                            state.selectedTheme.asString(),
                            onClick = { viewModel.onEvent(SettingsEvent.ShowThemeOptionsDialog(true)) }
                        )
                        Spacer(modifier = Modifier.height(betweenFieldsMargin))
                        SettingsField(
                            stringResource(R.string.settings_font),
                            state.selectedFont.asString(),
                            onClick = { viewModel.onEvent(SettingsEvent.ShowFontOptionsDialog(true)) }
                        )
                        Spacer(modifier = Modifier.height(betweenFieldsMargin))
                        SwitchField(
                            stringResource(id = R.string.settings_show_greeting),
                            showGreetingState.value.asString(),
                            showGreetingState.isActive,
                            onSelect = {
                                viewModel.onEvent(SettingsEvent.ChangeShowGreetingState)
                            },
                            switchTestTag = TestTags.SHOW_GREETING_SWITCH,
                            valueTestTag = TestTags.SHOW_GREETING_SWITCH_VALUE
                        )
                        Spacer(modifier = Modifier.height(betweenFieldsMargin))
                        SwitchField(
                            stringResource(id = R.string.settings_twenty_four_hour_clock),
                            twentyFourHourClockState.value.asString(),
                            twentyFourHourClockState.isActive,
                            onSelect = {
                                viewModel.onEvent(SettingsEvent.ChangeTwentyFourHourClockState)
                            },
                            switchTestTag = TestTags.TWENTY_FOUR_HOUR_SWITCH,
                            valueTestTag = TestTags.TWENTY_FOUR_HOUR_SWITCH_VALUE
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(color = DividerGrey, modifier = Modifier.padding(horizontal = generalMargin))
                    Column(settingsColumnModifier) {
                        Spacer(modifier = Modifier.height(headerTopMargin))
                        SettingsHeader(stringResource(id = R.string.settings_header_about))
                        Spacer(modifier = Modifier.height(betweenFieldsMargin))
                        SettingsField(
                            stringResource(id = R.string.settings_rate_app_title),
                            stringResource(id = R.string.settings_rate_app_message),
                            onClick = { context.openGooglePlayAppPage() }
                        )
                        Spacer(modifier = Modifier.height(betweenFieldsMargin))
                        SettingsField(
                            stringResource(id = R.string.settings_share_app_title),
                            stringResource(id = R.string.settings_share_app_message),
                            onClick = { context.launchShareAppIntent() }
                        )
                        Spacer(modifier = Modifier.height(betweenFieldsMargin))
                        SettingsField(
                            stringResource(id = R.string.settings_privacy_policy_title),
                            stringResource(id = R.string.settings_privacy_policy_message),
                            onClick = { context.openPrivacyPolicyPage() }
                        )
                        Spacer(modifier = Modifier.height(betweenFieldsMargin))
                        SettingsField(
                            stringResource(id = R.string.settings_app_version_title),
                            BuildConfig.VERSION_NAME,
                            onClick = { }
                        )
                    }
                }
            }
            OptionsDialog(
                title = stringResource(id = R.string.settings_theme_dialog_title),
                options = geThemesTextList(),
                selected = state.selectedTheme.asString(),
                visible = state.showThemeOptionsDialog,
                onOptionSelected = {
                    viewModel.onEvent(SettingsEvent.SelectThemeOption(it))
                }
            ) {
                viewModel.onEvent(SettingsEvent.ShowThemeOptionsDialog(false))
            }
            OptionsDialog(
                title = stringResource(id = R.string.settings_font_dialog_title),
                options = geFontsTextList(),
                selected = state.selectedFont.asString(),
                visible = state.showFontOptionsDialog,
                onOptionSelected = {
                    viewModel.onEvent(SettingsEvent.SelectFontOption(it))
                }
            ) {
                viewModel.onEvent(SettingsEvent.ShowFontOptionsDialog(false))
            }
        }
    )
}
