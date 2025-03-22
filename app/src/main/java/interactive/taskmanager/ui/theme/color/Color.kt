@file:Suppress("MatchingDeclarationName")
package interactive.taskmanager.ui.theme.color

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
@Suppress("LongParameterList")
class Colors(
    val colorPrimary: Color,
    val colorPrimaryDark: Color,
    val colorAccent: Color,
    val colorSecondary: Color,
    val textPrimary: Color, // android:textColorPrimary
    val textSecondary: Color, // android:textColorSecondary
    val textTertiary: Color,
    val textPrimaryInverse: Color, // android:textColorPrimaryInverse
    val textSecondaryInverse: Color, // android:textColorSecondaryInverse
    val statusBarColor: Color, // android:statusBarColor
    val backgroundPrimary: Color,
    val backgroundSecondary: Color,
    val content: Color,
    val alertDialogAction: Color,
    val hyperlink: Color,
    val error: Color,
    val isDark: Boolean,
)

val LightColors = Colors(
    backgroundPrimary = LightColor.backgroundPrimary,
    backgroundSecondary = LightColor.backgroundSecondary,
    content = LightColor.content,
    textPrimary = LightColor.textPrimary,
    colorPrimary = LightColor.primary,
    colorPrimaryDark = LightColor.primaryDark,
    colorAccent = LightColor.accent,
    colorSecondary = LightColor.secondary,
    textSecondary = LightColor.textSecondary,
    textTertiary = LightColor.textTertiary,
    textPrimaryInverse = LightColor.textPrimaryInverse,
    textSecondaryInverse = LightColor.textSecondaryInverse,
    statusBarColor = LightColor.statusBarColor,
    alertDialogAction = LightColor.alertDialogAction,
    error = LightColor.error,
    hyperlink = LightColor.hyperlink,
    isDark = false,
)

val DarkColors = Colors(
    backgroundPrimary = DarkColor.backgroundPrimary,
    backgroundSecondary = DarkColor.backgroundSecondary,
    content = DarkColor.content,
    textPrimary = DarkColor.textPrimary,
    colorPrimary = DarkColor.primary,
    colorPrimaryDark = DarkColor.primaryDark,
    colorAccent = DarkColor.accent,
    colorSecondary = DarkColor.secondary,
    textSecondary = DarkColor.textSecondary,
    textTertiary = DarkColor.textTertiary,
    textPrimaryInverse = DarkColor.textPrimaryInverse,
    textSecondaryInverse = DarkColor.textSecondaryInverse,
    statusBarColor = DarkColor.statusBar,
    alertDialogAction = DarkColor.alertDialogAction,
    error = DarkColor.error,
    hyperlink = DarkColor.hyperlink,
    isDark = true,
)
