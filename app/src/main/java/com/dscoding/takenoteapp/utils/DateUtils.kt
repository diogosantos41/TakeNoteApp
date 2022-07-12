package com.dscoding.takenoteapp.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*


object DateUtils {

    const val TWENTY_FOUR_HOUR_DATE_FORMAT = "MMMM d, HH:mm"
    const val THIRTEEN_HOUR_DATE_FORMAT = "MMMM d, hh:mm aa"

    @SuppressLint("SimpleDateFormat")
    fun convertTimeMillisToStringDate(timeMillis: Long?, format: String): String {
        val simpleDateFormat = SimpleDateFormat(format)
        val date = timeMillis?.let { Date(it) }
        return simpleDateFormat.format(date)
    }
}