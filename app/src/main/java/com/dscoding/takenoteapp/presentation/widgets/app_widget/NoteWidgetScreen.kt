package com.dscoding.takenoteapp.presentation.widgets.app_widget


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.ActionParameters
import androidx.glance.action.actionParametersOf
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.background
import androidx.glance.layout.*
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.dscoding.takenoteapp.presentation.MainActivity
import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.presentation.widgets.components.GlanceSpacer
import com.dscoding.takenoteapp.presentation.widgets.components.GlanceVerticalDivider
import com.dscoding.takenoteapp.presentation.widgets.note_selection.NoteWidgetSelectionActivity
import com.dscoding.takenoteapp.ui.theme.DarkerGrey
import com.dscoding.takenoteapp.common.Constants.NOTE_WIDGET_COLOR_ARG
import com.dscoding.takenoteapp.common.Constants.NOTE_WIDGET_ID_ARG


@ExperimentalFoundationApi
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NoteWidgetScreen(noteWidgetState: State<NoteWidgetState>) {
    Box(
        GlanceModifier
            .fillMaxSize()
            .background(ColorProvider(color = Color(noteWidgetState.value.noteColor)))
    ) {
        Row(
            modifier = GlanceModifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.Horizontal.CenterHorizontally,
            ) {
                Image(
                    provider = ImageProvider(R.drawable.background_round_edit_icon),
                    contentDescription = "Widget Edit Note",
                    modifier = GlanceModifier
                        .size(28.dp)
                        .clickable(
                            actionStartActivity<MainActivity>(
                                actionParametersOf(
                                    ActionParameters.Key<Int>(NOTE_WIDGET_ID_ARG) to noteWidgetState.value.noteId,
                                    ActionParameters.Key<Int>(NOTE_WIDGET_COLOR_ARG) to noteWidgetState.value.noteColor
                                )
                            )
                        )
                )
                GlanceSpacer(height = 10.dp)
                Image(
                    provider = ImageProvider(R.drawable.background_round_list_icon),
                    contentDescription = "Widget List Note",
                    modifier = GlanceModifier
                        .size(28.dp)
                        .clickable(actionStartActivity<NoteWidgetSelectionActivity>())
                )
            }
            GlanceSpacer(width = 12.dp)
            GlanceVerticalDivider()
            GlanceSpacer(width = 12.dp)
            Column(
                modifier = GlanceModifier
                    .fillMaxSize()
            ) {
                Text(
                    text = noteWidgetState.value.noteTitle,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = ColorProvider(color = DarkerGrey)
                    ),
                    maxLines = 1
                )
                GlanceSpacer(height = 6.dp)
                Text(
                    text = noteWidgetState.value.noteContent,
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = ColorProvider(color = DarkerGrey),
                    ),
                    maxLines = 10,
                )
                GlanceSpacer(height = 10.dp)
            }
        }
    }
}

