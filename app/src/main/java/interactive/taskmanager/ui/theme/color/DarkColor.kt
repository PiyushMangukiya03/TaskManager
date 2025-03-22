package interactive.taskmanager.ui.theme.color

import androidx.compose.ui.graphics.Color

// omitting "color" prefix
internal object DarkColor {
    internal val primary = globalBlue
    internal val primaryDark = globalBlue
    internal val textPrimary = colorTextPrimaryInverse
    internal val textPrimaryInverse = colorTextPrimary
    internal val secondary = globalBlue
    internal val textSecondary = colorTextSecondaryInverse
    internal val textTertiary = colorTextTertiary
    internal val textSecondaryInverse = colorTextSecondary
    internal val accent = colorPrimary
    internal val backgroundPrimary = globalBlack
    internal val backgroundSecondary = Color(0xFF2D2C2A) // from where?
    internal val content = globalWhite // ???
    internal val statusBar = colorStatusBar
    internal val alertDialogAction = alertDialogActionColor
    internal val error = globalRed
    internal val hyperlink = hyperlinkColor
}
