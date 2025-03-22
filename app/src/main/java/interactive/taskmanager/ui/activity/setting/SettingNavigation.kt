package interactive.taskmanager.ui.activity.setting

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import interactive.taskmanager.ui.utility.extension.slidingComposable
import kotlinx.serialization.Serializable

@Serializable
data object SettingPage

fun NavController.navigateToSetting(
    navOptions: NavOptions? = androidx.navigation.navOptions { launchSingleTop = true },
) {
    navigate(
        route = SettingPage,
        navOptions = navOptions,
    )
}

internal fun NavGraphBuilder.settingPage(
    onBackClick: () -> Unit
) {
    slidingComposable<SettingPage> {
        SettingScreen(
            onBackClick = onBackClick
        )
    }
}