package com.dscoding.takenoteapp

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.dscoding.takenoteapp.presentation.add_edit_note.AddEditNoteScreen
import com.dscoding.takenoteapp.presentation.list_notes.NotesScreen
import com.dscoding.takenoteapp.presentation.search_notes.SearchNotesScreen
import com.dscoding.takenoteapp.presentation.settings.SettingsScreen
import com.dscoding.takenoteapp.presentation.util.Screen
import com.dscoding.takenoteapp.utils.Constants
import com.dscoding.takenoteapp.utils.Constants.NAVIGATION_ANIMATION_DURATION
import com.dscoding.takenoteapp.utils.Constants.NAVIGATION_SLIDE_HORIZONTAL_ANIMATION_TARGET_OFFSET
import com.dscoding.takenoteapp.utils.Constants.NOTE_INVALID_COLOR
import com.dscoding.takenoteapp.utils.Constants.NOTE_INVALID_ID
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalAnimationApi
@Composable
fun Navigation() {

    val navController = rememberAnimatedNavController()
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.NotesScreen.route
    ) {
        composable(
            route = Screen.NotesScreen.route,
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = NAVIGATION_ANIMATION_DURATION)) },
            popEnterTransition = { fadeIn(animationSpec = tween(durationMillis = NAVIGATION_ANIMATION_DURATION)) },
            enterTransition = { fadeIn(animationSpec = tween(durationMillis = NAVIGATION_ANIMATION_DURATION)) },
            ) {
            NotesScreen(navController = navController)
        }
        composable(
            route = Screen.AddEditNoteScreen.route +
                    "?${Constants.NOTE_ID_ARG}={${Constants.NOTE_ID_ARG}}&${Constants.NOTE_COLOR_ARG}={${Constants.NOTE_COLOR_ARG}}",
            arguments = listOf(
                navArgument(
                    name = Constants.NOTE_ID_ARG
                ) {
                    type = NavType.IntType
                    defaultValue = NOTE_INVALID_ID
                },
                navArgument(
                    name = Constants.NOTE_COLOR_ARG
                ) {
                    type = NavType.IntType
                    defaultValue = NOTE_INVALID_COLOR
                },
            ),
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = NAVIGATION_ANIMATION_DURATION)) },
            enterTransition = { fadeIn(animationSpec = tween(durationMillis = NAVIGATION_ANIMATION_DURATION)) }
        ) {
            val color = it.arguments?.getInt(Constants.NOTE_COLOR_ARG) ?: NOTE_INVALID_COLOR
            AddEditNoteScreen(
                navController = navController,
                noteColor = color
            )
        }
        composable(route = Screen.SearchNotesScreen.route,
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { NAVIGATION_SLIDE_HORIZONTAL_ANIMATION_TARGET_OFFSET },
                    animationSpec = tween(
                        durationMillis = NAVIGATION_ANIMATION_DURATION,
                        easing = FastOutSlowInEasing
                    )
                )
            },
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { NAVIGATION_SLIDE_HORIZONTAL_ANIMATION_TARGET_OFFSET },
                    animationSpec = tween(
                        durationMillis = NAVIGATION_ANIMATION_DURATION,
                        easing = FastOutSlowInEasing
                    )
                )
            }) {
            SearchNotesScreen(navController = navController)
        }
        composable(route = Screen.SettingsScreen.route,
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = NAVIGATION_ANIMATION_DURATION)) },
            enterTransition = { fadeIn(animationSpec = tween(durationMillis = NAVIGATION_ANIMATION_DURATION)) }) {
            SettingsScreen(navController = navController)
        }
    }
}