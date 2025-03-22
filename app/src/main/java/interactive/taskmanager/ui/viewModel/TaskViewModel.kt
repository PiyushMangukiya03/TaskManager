package interactive.taskmanager.ui.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import interactive.taskmanager.di.entity.TaskEntity
import interactive.taskmanager.ui.activity.addTask.AddTaskPage
import interactive.taskmanager.ui.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class TaskFilter { ALL, COMPLETED, PENDING }
enum class TaskSort { PRIORITY, DUE_DATE, TITLE }
val priorityOrder = mapOf(
    "High" to 3,
    "Medium" to 2,
    "Low" to 1
)

@HiltViewModel
class TaskViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: TaskRepository
) : ViewModel() {
    val args = AddTaskPage(savedStateHandle)

    val _filter = MutableStateFlow(TaskFilter.ALL)
    val _sort = MutableStateFlow(TaskSort.TITLE)

    private val _tasks = repository.allTasks // Flow<List<TaskEntity>>

    val allTasks = repository.allTasks
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val filteredSortedTasks = combine(_tasks, _filter, _sort) { tasks, filter, sort ->
        tasks
            .filter {
                when (filter) {
                    TaskFilter.ALL -> true
                    TaskFilter.COMPLETED -> it.isCompleted
                    TaskFilter.PENDING -> !it.isCompleted
                }
            }
            .sortedWith(
                when (sort) {
                    TaskSort.PRIORITY -> compareByDescending { priorityOrder[it.priority] ?: 0 }
                    TaskSort.DUE_DATE -> compareBy { it.dueDate }
                    TaskSort.TITLE -> compareBy { it.title }
                }
            )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun setFilter(filter: TaskFilter) {
        _filter.value = filter
    }

    fun setSort(sort: TaskSort) {
        _sort.value = sort
    }

    fun getTaskById(taskId: Int, onResult: (TaskEntity?) -> Unit) {
        viewModelScope.launch {
            val task = repository.getTaskById(taskId)
            onResult(task)
        }
    }

    fun addTask(task: TaskEntity) {
        viewModelScope.launch { repository.addTask(task) }
    }

    fun updateTask(task: TaskEntity) {
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }

    fun deleteTask(task: TaskEntity) {
        viewModelScope.launch { repository.deleteTask(task) }
    }

    fun updateTaskCompletion(taskId: Int, isCompleted: Boolean) {
        viewModelScope.launch { repository.updateTaskCompletion(taskId, isCompleted) }
    }
}
