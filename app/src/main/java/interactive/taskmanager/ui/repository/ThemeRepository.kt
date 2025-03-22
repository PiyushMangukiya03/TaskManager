package interactive.taskmanager.ui.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

enum class AppThemeMode {
    LIGHT, DARK, SYSTEM
}

class ThemeRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {

}
