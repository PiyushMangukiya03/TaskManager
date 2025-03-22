package interactive.taskmanager.ui.repository

import interactive.taskmanager.di.database.TaskDao
import interactive.taskmanager.di.entity.TaskEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Suppress("LongParameterList")
class TaskRepository @Inject constructor(
    private val dao: TaskDao
) {
    val allTasks: Flow<List<TaskEntity>> = dao.getAllTasks()

    suspend fun getTaskById(taskId: Int): TaskEntity? = dao.getTaskById(taskId)

    suspend fun addTask(task: TaskEntity) {
        dao.insertTask(task)
    }

    suspend fun updateTask(task: TaskEntity) = dao.updateTask(task)

    suspend fun deleteTask(task: TaskEntity) = dao.deleteTask(task)

    suspend fun updateTaskCompletion(taskId: Int, isCompleted: Boolean) {
        dao.updateTaskCompletion(taskId, isCompleted)
    }
}
