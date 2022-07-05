package com.dscoding.takenoteapp.utils

import androidx.navigation.NavController
import com.dscoding.takenoteapp.presentation.util.Screen

fun NavController.safeNavigate(destinationRoute: String) {
    if (this.currentDestination?.route != destinationRoute) {
        this.navigate(destinationRoute)
    }
}

fun NavController.popBackToDashboard() {
    this.navigate(Screen.NotesScreen.route) {
        popUpTo(Screen.NotesScreen.route) {
            inclusive = true
        }
    }
}