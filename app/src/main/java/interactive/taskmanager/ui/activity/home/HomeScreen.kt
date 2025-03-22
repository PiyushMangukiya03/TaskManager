package interactive.taskmanager.ui.activity.home

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.intuit.sdp.R
import interactive.taskmanager.di.entity.TaskEntity
import interactive.taskmanager.ui.activity.home.filter.RoundedCornerDialog
import interactive.taskmanager.ui.activity.home.state.EmptyTaskState
import interactive.taskmanager.ui.theme.TaskManagerTheme
import interactive.taskmanager.ui.theme.TypeTheme
import interactive.taskmanager.ui.viewModel.TaskViewModel
import interactive.taskmanager.ui.utility.button.AnimatedFAB

@Composable
internal fun HomeScreen(
    onSetting: () -> Unit,
    onAddTask: () -> Unit,
    onDetails: (TaskEntity) -> Unit,
    viewModel: TaskViewModel = hiltViewModel()
) {
    val snackBarHostState = remember { SnackbarHostState() }

    val allTasks by viewModel.allTasks.collectAsState()
    val completedCount = allTasks.count { it.isCompleted }
    val totalCount = allTasks.size

    val tasks by viewModel.filteredSortedTasks.collectAsState()

    var showDialog by remember { mutableStateOf(false) }
    val filter by viewModel._filter.collectAsState()
    val sort by viewModel._sort.collectAsState()

    val margin10 = dimensionResource(id = R.dimen._10sdp)
    val margin15 = dimensionResource(id = R.dimen._15sdp)
    val margin60 = dimensionResource(id = R.dimen._60sdp)

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (header, content, column, fab, emptyState) = createRefs()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(TypeTheme.colors.backgroundPrimary)
                .padding(margin15)
                .constrainAs(header) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            ProfileHeader(onSettingClick = {
                onSetting()
            }, onFilterClick = {
                showDialog = true
            })
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(content) {
                    start.linkTo(parent.start, margin = margin15)
                    end.linkTo(parent.end, margin = margin15)
                    top.linkTo(header.bottom)
                    width = Dimension.fillToConstraints
                },
            shape = RoundedCornerShape(dimensionResource(id = R.dimen._12sdp)),
            elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(id = R.dimen._6sdp)),
            colors = CardDefaults.cardColors(containerColor = TypeTheme.colors.backgroundSecondary),
        ) {
            TaskCompletionProgress(
                completedTasks = completedCount,
                totalTasks = totalCount,
                modifier = Modifier
                    .padding(margin10)
                    .fillMaxWidth()
            )
        }

        if (tasks.isNotEmpty()){
            LazyColumn(
                modifier = Modifier
                    .constrainAs(column) {
                        top.linkTo(content.bottom, margin10)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                    },
                contentPadding = PaddingValues(top = margin10, bottom = margin60)
            ) {
                items(tasks) { task ->
                    TaskComponent(snackBarHostState, task,
                        onDelete = {
                            viewModel.deleteTask(it)
                        }, onComplete = {
                            viewModel.updateTaskCompletion(it.id, true)
                        }, onUndoDelete = {

                        }, onUndoComplete = {

                        }, onClick = {
                            onDetails(it)
                        }
                    )
                    Spacer(modifier = Modifier.height(margin10))
                }
            }
        } else {
            EmptyTaskState(modifier = Modifier.constrainAs(emptyState){
                top.linkTo(content.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }, onAddTaskClick = onAddTask)
        }

        AnimatedFAB(modifier = Modifier.constrainAs(fab) {
            end.linkTo(parent.end, margin = margin10)
            bottom.linkTo(parent.bottom, margin = margin10)
        }, onClick = onAddTask)
    }

    if (showDialog) {
        RoundedCornerDialog(
            selectedSort = sort,
            selectedFilter = filter,
            onSortSelected = viewModel::setSort,
            onFilterSelected = viewModel::setFilter,
            onDismiss = { showDialog = false }
        )
    }
}

@Preview
@Composable
private fun HomePreview() {
    TaskManagerTheme(isSystemInDarkTheme()) {
        HomeScreen(
            onSetting = {},
            onAddTask = {},
            onDetails = {}
        )
    }
}