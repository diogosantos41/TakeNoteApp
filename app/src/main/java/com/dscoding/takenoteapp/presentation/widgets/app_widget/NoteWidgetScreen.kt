package com.dscoding.takenoteapp.presentation.widgets.app_widget


import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
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
import com.dscoding.takenoteapp.MainActivity
import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.ui.theme.DarkerGrey
import com.dscoding.takenoteapp.utils.Constants.NOTE_WIDGET_COLOR_ARG
import com.dscoding.takenoteapp.utils.Constants.NOTE_WIDGET_ID_ARG

@Composable
fun NoteWidgetScreen(noteWidgetState: State<NoteWidgetState>) {
    Box(
        GlanceModifier
            .width(200.dp).height(150.dp)
            .background(ImageProvider(R.drawable.background_widget))
    ) {
        Column(
            modifier = GlanceModifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(end = 32.dp)
                .clickable(
                    actionStartActivity<MainActivity>(
                        actionParametersOf(
                            ActionParameters.Key<Int>(NOTE_WIDGET_ID_ARG) to noteWidgetState.value.noteId,
                            ActionParameters.Key<Int>(NOTE_WIDGET_COLOR_ARG) to noteWidgetState.value.noteColor
                        )
                    )
                )
        ) {
            Text(
                text = noteWidgetState.value.noteTitle,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = ColorProvider(color = DarkerGrey),
                ),
                maxLines = 1,

                )
            Box(modifier = GlanceModifier.height(10.dp)) {}
            Text(
                text = noteWidgetState.value.noteContent,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = ColorProvider(color = DarkerGrey),
                ),
                maxLines = 10,
            )
        }
    }
}
