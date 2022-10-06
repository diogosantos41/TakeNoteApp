package com.dscoding.takenoteapp.presentation

import android.content.Context
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.core.app.ApplicationProvider
import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.assertNodeEqualsText
import com.dscoding.takenoteapp.delay
import com.dscoding.takenoteapp.di.DataModule
import com.dscoding.takenoteapp.di.UseCaseModule
import com.dscoding.takenoteapp.ui.theme.TakeNoteAppTheme
import com.dscoding.takenoteapp.utils.TestTags
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
@UninstallModules(DataModule::class, UseCaseModule::class)
class TakeNoteEndToEndTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @OptIn(ExperimentalAnimationApi::class)
    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.activity.setContent {
            TakeNoteAppTheme {
                Navigation()
            }
        }
    }

    @Test
    fun saveNewNote_editAfterwards() {
        val noteTestTitle = "note-test-title"
        val noteTestTitleEdited = "edited-note-test-title"
        val noteTestContent = "note-test-content"

        // Click on FAB - Navigate to Add Note Screen
        composeRule.onNodeWithContentDescription(context.getString(R.string.notes_content_description_add))
            .performClick()

        // Enter texts and save the note - Navigate to Notes Screen
        composeRule.onNodeWithTag(TestTags.TITLE_TEXT_FIELD).performTextInput(noteTestTitle)
        composeRule.onNodeWithTag(TestTags.CONTENT_TEXT_FIELD).performTextInput(noteTestContent)
        composeRule.onNodeWithContentDescription(context.getString(R.string.add_edit_note_content_description_save))
            .performClick()

        // Check that note exists - Navigate to Edit Note Screen
        composeRule.onNodeWithText(noteTestTitle).assertIsDisplayed()
        composeRule.onNodeWithText(noteTestTitle).performClick()

        // Change text and save the note - Navigate to Notes Screen
        composeRule.onNodeWithTag(TestTags.TITLE_TEXT_FIELD).assertTextEquals(noteTestTitle)
        composeRule.onNodeWithTag(TestTags.CONTENT_TEXT_FIELD).assertTextEquals(noteTestContent)
        composeRule.onNodeWithTag(TestTags.TITLE_TEXT_FIELD).performTextReplacement(noteTestTitleEdited)
        composeRule.onNodeWithContentDescription(context.getString(R.string.add_edit_note_content_description_edit))
            .performClick()

        // Check that note was edited
        composeRule.onNodeWithText(noteTestTitleEdited).assertIsDisplayed()
    }

    @Test
    fun saveNewNotes_orderByTitleDescending() {

        //Add 3 Notes
        for (i in 1..3) {
            // Click on FAB - Navigate to Add Note Screen
            composeRule.onNodeWithContentDescription(context.getString(R.string.notes_content_description_add))
                .performClick()

            // Enter texts and save the note - Navigate to Notes Screen
            composeRule.onNodeWithTag(TestTags.TITLE_TEXT_FIELD).performTextInput(i.toString())
            composeRule.onNodeWithTag(TestTags.CONTENT_TEXT_FIELD).performTextInput(i.toString())
            composeRule.onNodeWithContentDescription(context.getString(R.string.add_edit_note_content_description_save))
                .performClick()
        }
        composeRule.onNodeWithText("1").assertIsDisplayed()
        composeRule.onNodeWithText("2").assertIsDisplayed()
        composeRule.onNodeWithText("3").assertIsDisplayed()

        // Order by title descending
        composeRule.onNodeWithContentDescription(context.getString(R.string.notes_content_description_sort))
            .performClick()
        composeRule.onNodeWithTag(TestTags.ORDER_TITLE_RADIO_BUTTON)
            .performClick()
        composeRule.onNodeWithTag(TestTags.ORDER_DESCENDING_RADIO_BUTTON)
            .performClick()

        // Check that the order was changed
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[0]
            .assertTextContains("3")
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[1]
            .assertTextContains("2")
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[2]
            .assertTextContains("1")

    }

    @Ignore("Not ready, Swipe left/right not working")
    @Test
    fun toggleSettingsShowGreeting_ToggleAgainAfterwards() {
        composeRule.onNodeWithText(context.getString(R.string.notes_greeting_title))
            .assertIsDisplayed()
        composeRule.onNodeWithContentDescription(context.getString(R.string.notes_content_description_settings))
            .performClick()

        composeRule.onNodeWithTag(TestTags.SHOW_GREETING_SWITCH)
            .performTouchInput { swipeLeft() }
        assertNodeEqualsText(
            node = composeRule.onNodeWithTag(TestTags.SHOW_GREETING_SWITCH_VALUE),
            text = context.getString(R.string.generic_disabled)
        )
        composeRule.onNodeWithContentDescription(context.getString(R.string.settings_content_description_back))
            .performClick()

        composeRule.onNodeWithText(context.getString(R.string.notes_greeting_title))
            .assertIsNotDisplayed()
    }

    @Ignore("Not ready, -IllegalStateException FocusRequester not initialized-")
    @Test
    fun saveNewNotes_SearchByText() {

        val text1 = "A"
        val text2 = "AB" // contains text 1
        val text3 = "ABC" // contains text 1 and 2
        val noteTextList = arrayListOf(text1, text2, text3)

        // Add 3 Notes
        for (text in noteTextList) {
            // Click on FAB - Navigate to Add Note Screen
            composeRule.onNodeWithContentDescription(context.getString(R.string.notes_content_description_add))
                .performClick()

            // Enter texts and save the note - Navigate to Notes Screen
            composeRule.onNodeWithTag(TestTags.TITLE_TEXT_FIELD).performTextInput(text)
            composeRule.onNodeWithTag(TestTags.CONTENT_TEXT_FIELD).performTextInput(text)
            composeRule.onNodeWithContentDescription(context.getString(R.string.add_edit_note_content_description_save))
                .performClick()
        }

        // Check notes were added - Navigate to search screen
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM).assertCountEquals(3)
        composeRule.onNodeWithContentDescription(context.getString(R.string.notes_content_description_search))
            .performClick()

        // Search with different texts
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM).assertCountEquals(3)
        composeRule.onNodeWithTag(TestTags.SEARCH_QUERY_TEXT_FIELD).performTextInput(text1)
        composeRule.delay(300)
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM).assertCountEquals(3)
        composeRule.onNodeWithTag(TestTags.SEARCH_QUERY_TEXT_FIELD).performTextInput(text2)
        composeRule.delay(300)
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM).assertCountEquals(2)
        composeRule.onNodeWithTag(TestTags.SEARCH_QUERY_TEXT_FIELD).performTextInput(text3)
        composeRule.delay(300)
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM).assertCountEquals(1)
        composeRule.onNodeWithTag(TestTags.SEARCH_QUERY_TEXT_FIELD).performTextInput("0 notes will appear after this search")
        composeRule.onNodeWithText(context.getString(R.string.notes_search_empty_message))
        composeRule.onNodeWithContentDescription(context.getString(R.string.notes_search_content_description_close))
            .performClick()
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM).assertCountEquals(3)
    }
}