@file:OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)

package com.dscoding.takenoteapp.presentation.settings

import android.content.Context
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import com.dscoding.takenoteapp.presentation.MainActivity
import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.assertNodeEqualsText
import com.dscoding.takenoteapp.di.DataModule
import com.dscoding.takenoteapp.di.UseCaseModule
import com.dscoding.takenoteapp.presentation.util.Screen
import com.dscoding.takenoteapp.ui.theme.TakeNoteAppTheme
import com.dscoding.takenoteapp.common.TestTags
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(DataModule::class, UseCaseModule::class)
class SettingsScreenTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @ExperimentalAnimationApi
    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.activity.setContent {
            val navController = rememberNavController()
            TakeNoteAppTheme {
                NavHost(
                    navController = navController, startDestination = Screen.SettingsScreen.route
                ) {
                    composable(route = Screen.SettingsScreen.route) {
                        SettingsScreen(navController = navController)
                    }
                }
            }
        }
    }

    @Test
    fun selectTheme_themeSelected() {
        composeRule.onNodeWithText(context.getString(R.string.settings_theme)).performClick()
        composeRule.onNodeWithText(context.getString(R.string.settings_theme_dialog_title))
            .assertIsDisplayed()
        composeRule.onNodeWithTag(TestTags.THEME_YELLOW_RADIO_BUTTON).performClick()
        composeRule.onNodeWithText(context.getString(R.string.settings_theme_option_dark_yellow))
            .assertIsDisplayed()
    }

    @Ignore("Not ready , Swipe left/right not working")
    @Test
    fun switchOffThenSwitchOnGreeting_SwitchIsOn() {
        composeRule.onNodeWithTag(TestTags.SHOW_GREETING_SWITCH)
            .performTouchInput { swipeLeft() }
        assertNodeEqualsText(
            node = composeRule.onNodeWithTag(TestTags.SHOW_GREETING_SWITCH_VALUE),
            text = context.getString(R.string.generic_disabled)
        )
        composeRule.onNodeWithTag(TestTags.SHOW_GREETING_SWITCH)
            .performTouchInput() { swipeRight() }
        assertNodeEqualsText(
            node = composeRule.onNodeWithTag(TestTags.SHOW_GREETING_SWITCH_VALUE),
            text = context.getString(R.string.generic_enabled)
        )
    }

    @Ignore("Not ready , Swipe left/right not working")
    @Test
    fun switchOffThenSwitchOnTwentyFourHours_SwitchIsOn() {
        composeRule.onNodeWithTag(TestTags.TWENTY_FOUR_HOUR_SWITCH)
            .performTouchInput { swipeLeft() }
        assertNodeEqualsText(
            node = composeRule.onNodeWithTag(TestTags.TWENTY_FOUR_HOUR_SWITCH_VALUE),
            text = context.getString(R.string.generic_disabled)
        )
        composeRule.onNodeWithTag(TestTags.TWENTY_FOUR_HOUR_SWITCH)
            .performTouchInput() { swipeRight() }
        assertNodeEqualsText(
            node = composeRule.onNodeWithTag(TestTags.TWENTY_FOUR_HOUR_SWITCH_VALUE),
            text = context.getString(R.string.generic_enabled)
        )
    }
}