package com.dscoding.takenoteapp.presentation

import android.R
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dscoding.takenoteapp.domain.data_store.SettingsDataStore
import com.dscoding.takenoteapp.domain.model.PreferencesDto
import com.dscoding.takenoteapp.ui.theme.TakeNoteAppTheme
import com.dscoding.takenoteapp.utils.Theme
import com.dscoding.takenoteapp.utils.getAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@ExperimentalFoundationApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var settingsDataStore: SettingsDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        var uiThemeState: UiThemeState by mutableStateOf(UiThemeState.Loading)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                getUiThemeState()
                    .onEach {
                        uiThemeState = it
                    }
                    .collect()
            }
        }

        splashScreen.setKeepOnScreenCondition {
            when (uiThemeState) {
                UiThemeState.Loading -> true
                is UiThemeState.Success -> false
            }
        }

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        setContent {
            TakeNoteAppTheme(theme = getAppTheme(getThemeId(uiThemeState))) {
                Surface {
                    // On some devices (ex: Poko X3) the screen became all white after system theme change.
                    // This code seems to solve the problem
                    lifecycleScope.launch {
                        delay(25)
                        window.setBackgroundDrawableResource(R.color.transparent)
                    }
                    Navigation()
                }
            }
        }
    }

    @Composable
    private fun getThemeId(
        uiState: UiThemeState,
    ): Int = when (uiState) {
        is UiThemeState.Loading -> Theme.SystemDefault.id
        is UiThemeState.Success -> uiState.preferencesDto.theme
    }

    private fun getUiThemeState(): StateFlow<UiThemeState> {
        return settingsDataStore.getPreferences().map {
            UiThemeState.Success(it)
        }.stateIn(
            scope = lifecycleScope,
            initialValue = UiThemeState.Loading,
            started = SharingStarted.WhileSubscribed(5_000)
        )
    }

    sealed interface UiThemeState {
        object Loading : UiThemeState
        data class Success(val preferencesDto: PreferencesDto) : UiThemeState
    }
}

