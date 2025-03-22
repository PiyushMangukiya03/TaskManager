package interactive.taskmanager.ui.activity.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import interactive.taskmanager.R
import interactive.taskmanager.di.entity.TaskEntity
import interactive.taskmanager.ui.activity.addTask.Priority
import interactive.taskmanager.ui.preview.Preview
import interactive.taskmanager.ui.theme.TypeTheme
import interactive.taskmanager.ui.theme.color.LightBlue
import interactive.taskmanager.ui.theme.color.colorTextSecondary
import interactive.taskmanager.ui.theme.color.globalBlack
import interactive.taskmanager.utils.UiModePreviews
import interactive.taskmanager.ui.utility.extension.toFormattedString
import interactive.taskmanager.ui.utility.swipe.DismissBackground
import kotlinx.coroutines.launch

@Composable
fun TaskComponent(
    snackBarHostState: SnackbarHostState,
    task: TaskEntity,
    onDelete: (TaskEntity) -> Unit,
    onComplete: (TaskEntity) -> Unit,
    onUndoDelete: (TaskEntity) -> Unit,
    onUndoComplete: (TaskEntity) -> Unit,
    onClick: (TaskEntity) -> Unit
) {
    val margin5 = dimensionResource(id = com.intuit.sdp.R.dimen._5sdp)
    val margin8 = dimensionResource(id = com.intuit.sdp.R.dimen._8sdp)
    val margin15 = dimensionResource(id = com.intuit.sdp.R.dimen._15sdp)
    val sp8 = dimensionResource(id = com.intuit.ssp.R.dimen._8ssp).value.sp
    val sp10 = dimensionResource(id = com.intuit.ssp.R.dimen._10ssp).value.sp
    val sp12 = dimensionResource(id = com.intuit.ssp.R.dimen._12ssp).value.sp
    val coroutineScope = rememberCoroutineScope()
    val currentItem by rememberUpdatedState(task)
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            when (it) {
                SwipeToDismissBoxValue.StartToEnd -> {
                    onDelete(currentItem)
                    coroutineScope.launch {
                        val result = snackBarHostState.showSnackbar(
                            message = "Task deleted",
                            actionLabel = "Undo"
                        )
                        if (result == SnackbarResult.ActionPerformed) {
                            onUndoDelete(currentItem)
                        }
                    }
                }

                SwipeToDismissBoxValue.EndToStart -> {
                    onComplete(currentItem)
                    coroutineScope.launch {
                        val result = snackBarHostState.showSnackbar(
                            message = "Task marked as completed",
                            actionLabel = "Undo"
                        )
                        if (result == SnackbarResult.ActionPerformed) {
                            onUndoComplete(currentItem)
                        }
                    }
                }

                SwipeToDismissBoxValue.Settled -> return@rememberSwipeToDismissBoxState false
            }
            return@rememberSwipeToDismissBoxState false
        },
        // positional threshold of 25%
        positionalThreshold = { it * .25f }
    )

    SwipeToDismissBox(
        state = dismissState,
        backgroundContent = { DismissBackground(TypeTheme.colors.textPrimary) },
        content = {
            ConstraintLayout(
                modifier = Modifier.fillMaxWidth()
            ) {
                val (content) = createRefs()

                val date = task.dueDate.toFormattedString("dd/MM/yyyy")
                Card(
                    modifier = Modifier
                        .clickable(onClick = { onClick(currentItem) })
                        .constrainAs(content) {
                            start.linkTo(parent.start, margin = margin15)
                            end.linkTo(parent.end, margin = margin15)
                            bottom.linkTo(parent.bottom)
                            top.linkTo(parent.top)
                            width = Dimension.fillToConstraints
                        },
                    shape = RoundedCornerShape(dimensionResource(id = com.intuit.sdp.R.dimen._12sdp)),
                    elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(id = com.intuit.sdp.R.dimen._6sdp)),
                    colors = CardDefaults.cardColors(containerColor = LightBlue),
                ) {
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                vertical = if (date.isEmpty() && task.description == null) margin15 else margin8,
                                horizontal = margin8
                            )
                    ) {
                        val (tvTitle, indicator, tvDescription, tvDateTime, tvCompleted) = createRefs()

                        Text(
                            text = task.title,
                            style = TypeTheme.typography.bodySmallBold.copy(fontSize = sp12),
                            color = globalBlack,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.constrainAs(tvTitle) {
                                start.linkTo(parent.start)
                                top.linkTo(parent.top)
                                end.linkTo(indicator.start, margin5)
                                width = Dimension.fillToConstraints
                            }
                        )

                        Icon(
                            painter = painterResource(id = R.drawable.ic_circle),
                            contentDescription = "",
                            tint = Priority.valueOf(task.priority).color,
                            modifier = Modifier.size(margin8).constrainAs(indicator){
                                top.linkTo(tvTitle.top)
                                end.linkTo(parent.end)
                            }
                        )

                        if (task.description != null) {
                            Text(
                                text = task.description,
                                style = TypeTheme.typography.bodySmallSemiBold.copy(fontSize = sp10),
                                color = colorTextSecondary,
                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.constrainAs(tvDescription) {
                                    start.linkTo(tvTitle.start)
                                    top.linkTo(tvTitle.bottom)
                                    end.linkTo(parent.end)
                                    width = Dimension.fillToConstraints
                                }
                            )
                        }

                        if (date.isNotEmpty()) {
                            Text(
                                text = date,
                                style = TypeTheme.typography.bodySmallSemiBold.copy(fontSize = sp8),
                                color = colorTextSecondary,
                                modifier = Modifier.constrainAs(tvDateTime) {
                                    top.linkTo(if (task.description != null) tvDescription.bottom else tvTitle.bottom, margin5)
                                    bottom.linkTo(parent.bottom)
                                    end.linkTo(parent.end)
                                }
                            )
                        }

                        if (task.isCompleted) {
                            Text(
                                text = "Completed",
                                style = TypeTheme.typography.bodySmallSemiBold.copy(fontSize = sp8),
                                color = colorTextSecondary,
                                modifier = Modifier.constrainAs(tvCompleted) {
                                    top.linkTo(if (task.description != null) tvDescription.bottom else tvTitle.bottom, margin5)
                                    bottom.linkTo(parent.bottom)
                                    end.linkTo(tvDateTime.start, margin = margin5)
                                }
                            )
                        }
                    }
                }
            }
        })
}

@UiModePreviews
@Composable
fun TaskPreview() {
    Preview {
        TaskComponent(
            SnackbarHostState(),
            TaskEntity(
                1,
                "Do Laundry",
                null,
                "10:00",
                0L,
                false
            ),
            onDelete = {},
            onComplete = {},
            onUndoDelete = {},
            onUndoComplete = {},
            onClick = {}
        )
    }
}