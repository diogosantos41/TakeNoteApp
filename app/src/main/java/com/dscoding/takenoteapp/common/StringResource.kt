package com.dscoding.takenoteapp.common

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

class StringResource(
    @StringRes val resId: Int,
    private vararg val args: Any
) {

    @Composable
    fun asString(): String {
        return stringResource(resId, *args)
    }

    fun asString(context: Context): String {
        return context.getString(resId, *args)
    }
}
