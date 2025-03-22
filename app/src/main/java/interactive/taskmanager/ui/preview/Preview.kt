package interactive.taskmanager.ui.preview

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import interactive.taskmanager.ui.theme.TaskManagerTheme

/**
 * Composable preview wrapper to provide all required CompositionLocals.
 */
@Composable
fun Preview(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    TaskManagerTheme(isDarkTheme) {
        content()
    }
}
