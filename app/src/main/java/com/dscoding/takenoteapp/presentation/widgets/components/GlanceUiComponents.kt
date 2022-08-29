package com.dscoding.takenoteapp.presentation.widgets.components

import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Build
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import androidx.core.graphics.drawable.toBitmap
import androidx.glance.BitmapImageProvider
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.*
import androidx.glance.unit.ColorProvider
import com.dscoding.takenoteapp.R

@Composable
fun GlanceSpacer(width: Dp = 1.dp, height: Dp = 1.dp) {
    Box(modifier = GlanceModifier.width(width).height(height)) {}
}

@Composable
fun GlanceHorizontalDivider() {
    Box(
        modifier = GlanceModifier
            .height(1.dp)
            .background(ColorProvider(R.color.black))
            .fillMaxWidth()
    ) {}
}

@Composable
fun GlanceVerticalDivider() {
    Box(
        modifier = GlanceModifier
            .width(1.dp)
            .background(ColorProvider(R.color.black))
            .fillMaxHeight()
    ) {}
}