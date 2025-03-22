package interactive.taskmanager.ui.viewModel

import android.content.Context
import android.content.res.Configuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import interactive.taskmanager.ui.repository.AppThemeMode
import interactive.taskmanager.utils.PreferencesManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager,
    @ApplicationContext private val context: Context
) : ViewModel() {

    // State to track the current theme mode
    private val _themeMode = MutableStateFlow(AppThemeMode.SYSTEM)
    val themeMode: StateFlow<AppThemeMode> = _themeMode

    init {
        // Load the initial theme preference
        viewModelScope.launch {
            preferencesManager.themeMode.collect { mode ->
                _themeMode.value = mode
            }
        }

        // Detect and save the system theme on first launch
        viewModelScope.launch {
            val currentTheme = preferencesManager.themeMode.first()
            if (currentTheme == AppThemeMode.SYSTEM) {
                // Detect the system theme
                val isSystemDarkMode = isSystemInDarkTheme()
                val systemTheme = if (isSystemDarkMode) AppThemeMode.DARK else AppThemeMode.LIGHT

                // Save the system theme to preferences
                preferencesManager.saveThemeMode(systemTheme)
                _themeMode.value = systemTheme
            }
        }
    }

    // Function to set the theme mode
    fun setTheme(mode: AppThemeMode) {
        viewModelScope.launch {
            _themeMode.value = mode
            preferencesManager.saveThemeMode(mode)
        }
    }

    // Helper function to check if the system is in dark mode
    private fun isSystemInDarkTheme(): Boolean {
        return when (context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> true
            else -> false
        }
    }
}
