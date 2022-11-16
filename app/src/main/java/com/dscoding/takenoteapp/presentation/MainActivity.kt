package com.dscoding.takenoteapp.presentation

import android.R
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.dscoding.takenoteapp.domain.data_store.SettingsDataStore
import com.dscoding.takenoteapp.domain.model.PreferencesDto
import com.dscoding.takenoteapp.ui.theme.TakeNoteAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@ExperimentalFoundationApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var settingsDataStore: SettingsDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContent {
            val preferencesState = settingsDataStore.getPreferences().collectAsState(initial = PreferencesDto())
            TakeNoteAppTheme(theme = com.dscoding.takenoteapp.utils.getTheme(preferencesState.value.theme)) {
                Surface {
                    // On some devices (ex: Poko X3) the screen became all white after system theme change.
                    // This code seems to solve the problem
                    lifecycleScope.launch {
                        delay(50)
                        window.setBackgroundDrawableResource(R.color.transparent)
                    }
                    Navigation()
                }
            }
        }
    }
}

