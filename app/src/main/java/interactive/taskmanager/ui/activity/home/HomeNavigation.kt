package interactive.taskmanager.ui.activity.home

import androidx.navigation.NavGraphBuilder
import interactive.taskmanager.di.entity.TaskEntity
import interactive.taskmanager.ui.utility.extension.slidingComposable
import kotlinx.serialization.Serializable

internal fun NavGraphBuilder.homePage(
    onSetting: () -> Unit,
    onAddTask: () -> Unit,
    onDetails: (TaskEntity) -> Unit,
) {
    slidingComposable<HomePage> {
        HomeScreen(
            onSetting = onSetting,
            onAddTask = onAddTask,
            onDetails = onDetails
        )
    }
}

@Serializable
data object HomePage