package com.dscoding.takenoteapp.utils

import android.os.Build

fun supportDynamicColors(): Boolean = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
