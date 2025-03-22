package interactive.taskmanager.utils

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "Light", group = "UI mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark", group = "UI mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
annotation class UiModePreviews
