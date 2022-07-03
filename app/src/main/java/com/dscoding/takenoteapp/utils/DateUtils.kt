package com.dscoding.takenoteapp.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    fun convertTimeMillisToStringDate(timeMillis: Long?): String {
        val simpleDateFormat = SimpleDateFormat("MMM d, HH:mm ");
        val date = timeMillis?.let { Date(it) };
        return simpleDateFormat.format(date)
    }
}