package interactive.taskmanager.ui.activity.addTask

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import interactive.taskmanager.di.entity.TaskEntity
import interactive.taskmanager.ui.utility.extension.slidingComposable
import kotlinx.serialization.Serializable

@Serializable
data class AddTaskPage(
    val id: Int
) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        id = savedStateHandle["id"] ?: -1
    )
}

internal fun NavGraphBuilder.addTask(
    onTaskAdded: () -> Unit
) {
    slidingComposable<AddTaskPage> {
        AddTaskScreen(
            onTaskAdded = onTaskAdded
        )
    }
}

fun NavController.navigateToAddTask(
    taskEntity: TaskEntity,
    navOptions: NavOptions? = androidx.navigation.navOptions { launchSingleTop = true },
) {
    navigate(
        route = AddTaskPage(id = taskEntity.id),
        navOptions = navOptions,
    )
}

fun NavController.navigateToAddTask(
    navOptions: NavOptions? = androidx.navigation.navOptions { launchSingleTop = true },
) {
    navigate(
        route = AddTaskPage(id = -1),
        navOptions = navOptions,
    )
}