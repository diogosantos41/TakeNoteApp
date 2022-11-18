package com.dscoding.takenoteapp.utils.extensions

import androidx.navigation.NavController
import com.dscoding.takenoteapp.presentation.util.Screen

fun NavController.safeNavigate(destinationRoute: String) {
    if (this.currentDestination?.route != destinationRoute) {
        this.navigate(destinationRoute)
    }
}