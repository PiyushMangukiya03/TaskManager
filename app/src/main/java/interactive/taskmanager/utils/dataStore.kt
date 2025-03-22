package interactive.taskmanager.utils

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import interactive.taskmanager.ui.repository.AppThemeMode
import interactive.taskmanager.utils.PreferencesManager.PreferencesKeys.THEME_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

// Create a DataStore instance
val Context.dataStore by preferencesDataStore(name = "settings")

@Singleton
class PreferencesManager @Inject constructor(
    private val context: Context
) {
    // Keys for SharedPreferences
    private object PreferencesKeys {
        val THEME_KEY = stringPreferencesKey("app_theme")
    }

    val themeMode: Flow<AppThemeMode> = context.dataStore.data
        .map { prefs ->
            when (prefs[THEME_KEY]) {
                "LIGHT" -> AppThemeMode.LIGHT
                "DARK" -> AppThemeMode.DARK
                else -> AppThemeMode.SYSTEM
            }
        }

    suspend fun saveThemeMode(mode: AppThemeMode) {
        context.dataStore.edit { it[THEME_KEY] = mode.name }
    }
}