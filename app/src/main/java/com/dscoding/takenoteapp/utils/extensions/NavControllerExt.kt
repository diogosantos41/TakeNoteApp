package com.dscoding.takenoteapp.utils.extensions

import androidx.navigation.NavController

fun NavController.safeNavigate(destinationRoute: String) {
    if (this.currentDestination?.route != destinationRoute) {
        this.navigate(destinationRoute)
    }
}