package interactive.taskmanager.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import interactive.taskmanager.ui.theme.color.Colors
import interactive.taskmanager.ui.theme.color.DarkColors
import interactive.taskmanager.ui.theme.color.LightColors

@Composable
fun TaskManagerTheme(
    darkTheme: Boolean,
    colors: Colors =
        if (darkTheme) DarkColors else LightColors,
    typography: TaskTypography = TaskAppTypography,
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    CompositionLocalProvider(
        LocalIsDarkTheme provides darkTheme,
        LocalContentColor provides colors.textPrimary,
        LocalColors provides colors,
        LocalTypography provides typography
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            shapes = Shapes,
            content = {
                // Override default text style
                ProvideTextStyle(
                    value = TypeTheme.typography.bodyMedium,
                    content = content,
                )
            }
        )
    }
}

object TypeTheme {
    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: TaskTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}

@Suppress("CompositionLocalAllowlist")
val LocalTypography = staticCompositionLocalOf<TaskTypography> {
    error("No Typography provided")
}

@Suppress("CompositionLocalAllowlist")
internal val LocalColors = staticCompositionLocalOf<Colors> {
    error("No Colors provided")
}

@Suppress("CompositionLocalAllowlist")
val LocalIsDarkTheme = compositionLocalOf { false }