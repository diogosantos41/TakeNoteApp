package com.dscoding.takenoteapp

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dscoding.takenoteapp.presentation.add_edit_note.AddEditNoteScreen
import com.dscoding.takenoteapp.presentation.labels.LabelsScreen
import com.dscoding.takenoteapp.presentation.list_notes.NotesScreen
import com.dscoding.takenoteapp.presentation.settings.SettingsScreen
import com.dscoding.takenoteapp.presentation.util.Screen
import com.dscoding.takenoteapp.ui.theme.TakeNoteAppTheme
import com.dscoding.takenoteapp.ui.theme.ThemeManager
import com.dscoding.takenoteapp.utils.Constants.NOTE_COLOR_ARG
import com.dscoding.takenoteapp.utils.Constants.NOTE_ID_ARG
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContent {
            TakeNoteAppTheme {
                Surface(color = ThemeManager.colors.backgroundColor) {
                    Navigation()
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalAnimationApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.NotesScreen.route
    ) {
        composable(route = Screen.NotesScreen.route) {
            NotesScreen(navController = navController)
        }
        composable(
            route = Screen.AddEditNoteScreen.route +
                    "?$NOTE_ID_ARG={$NOTE_ID_ARG}&$NOTE_COLOR_ARG={$NOTE_COLOR_ARG}",
            arguments = listOf(
                navArgument(
                    name = NOTE_ID_ARG
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument(
                    name = NOTE_COLOR_ARG
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
            )
        ) {
            val color = it.arguments?.getInt(NOTE_COLOR_ARG) ?: -1
            AddEditNoteScreen(
                navController = navController,
                noteColor = color
            )
        }
        composable(route = Screen.LabelsScreen.route) {
            LabelsScreen(navController = navController)
        }
        composable(route = Screen.SettingsScreen.route) {
            SettingsScreen(navController = navController)
        }
    }
}