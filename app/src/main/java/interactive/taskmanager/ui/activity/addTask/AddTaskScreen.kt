package interactive.taskmanager.ui.activity.addTask

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import interactive.taskmanager.R
import interactive.taskmanager.di.entity.TaskEntity
import interactive.taskmanager.ui.theme.TaskManagerTheme
import interactive.taskmanager.ui.theme.TypeTheme
import interactive.taskmanager.ui.theme.color.globalBlack
import interactive.taskmanager.ui.viewModel.TaskViewModel
import interactive.taskmanager.ui.utility.button.BrandedButton
import interactive.taskmanager.ui.utility.extension.clearFocusOnKeyboardDismiss
import interactive.taskmanager.ui.utility.extension.formattedDateStringOrPlaceholder
import interactive.taskmanager.ui.utility.extension.taskColors
import interactive.taskmanager.ui.utility.forms.InfoSection
import interactive.taskmanager.ui.utility.picker.PickerOpeningInput
import interactive.taskmanager.ui.utility.switch.CustomSwitch
import interactive.taskmanager.ui.utility.textfield.TextInput
import interactive.taskmanager.ui.utility.toolBar.ToolBar
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AddTaskScreen(
    onTaskAdded: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TaskViewModel = hiltViewModel()
) {

    val taskState = remember { mutableStateOf<TaskEntity?>(null) }
    var titleState by remember { mutableStateOf(TextFieldValue("")) }
    var descriptionState by remember { mutableStateOf(TextFieldValue("")) }
    var selectedPriority by remember { mutableStateOf(Priority.Medium) }
    var isCompletedState by remember { mutableStateOf(false) }
    val titleInputFocusRequester = remember { FocusRequester() }
    val descriptionInputFocusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val datePickerState = rememberDatePickerState(selectableDates = object : SelectableDates {
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            val instant = Instant.ofEpochMilli(utcTimeMillis)
            val date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
            return date.isAfter(LocalDateTime.now()) || date.dayOfYear == LocalDateTime.now().dayOfYear
        }

        override fun isSelectableYear(year: Int): Boolean {
            return year >= LocalDateTime.now().year
        }
    })
    var showDatePicker by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (viewModel.args.id != -1) {
            viewModel.getTaskById(viewModel.args.id) { task ->
                taskState.value = task
                titleState = TextFieldValue(task?.title ?: "")
                descriptionState = TextFieldValue(task?.description ?: "")
                selectedPriority = Priority.valueOf(task?.priority ?: Priority.Medium.name)
                datePickerState.selectedDateMillis = task?.dueDate
                isCompletedState = task?.isCompleted ?: false
            }
        }

        if (titleState.text.isEmpty()) {
            titleInputFocusRequester.requestFocus()
        }
    }

    val margin14 = dimensionResource(id = com.intuit.sdp.R.dimen._14sdp)

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(state = rememberScrollState())
    ) {
        val (toolBar, tiTitle, tiDescription, prioritySelector, datePicker, complete, btnAddTask) = createRefs()

        ToolBar(onBackClick = {
            onTaskAdded()
        },
            title = if (taskState.value != null) stringResource(R.string.task_details) else stringResource(
                R.string.add_task
            ),
            modifier = Modifier.constrainAs(toolBar) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

        InfoSection(title = "Task Title",
            modifier = Modifier
                .constrainAs(tiTitle) {
                    top.linkTo(toolBar.bottom, margin = margin14)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(horizontal = margin14)
        ) {
            TextInput(
                modifier = Modifier
                    .focusRequester(titleInputFocusRequester)
                    .clearFocusOnKeyboardDismiss()
                    .fillMaxWidth(),
                placeholder = stringResource(R.string.title),
                value = titleState,
                onValueChange = {
                    titleState = TextFieldValue(
                        text = it.text,
                        selection = TextRange(it.text.length), // Keep cursor at the right position
                    )
                },
                keyboardActions = KeyboardActions(
                    onNext = {
                        if (titleState.text.isNotEmpty()) {
                            descriptionInputFocusRequester.requestFocus()
                        }
                    },
                ),
                keyboardOptions = KeyboardOptions(
                    showKeyboardOnFocus = true,
                    imeAction = ImeAction.Next,
                )
            )
        }

        InfoSection(title = "Task Description",
            modifier = Modifier
                .padding(horizontal = margin14)
                .constrainAs(tiDescription) {
                    top.linkTo(tiTitle.bottom, margin = margin14)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
        {
            TextInput(
                modifier = Modifier
                    .focusRequester(descriptionInputFocusRequester)
                    .clearFocusOnKeyboardDismiss()
                    .fillMaxWidth(),
                placeholder = stringResource(R.string.description),
                value = descriptionState,
                onValueChange = {
                    descriptionState = TextFieldValue(
                        text = it.text,
                        selection = TextRange(it.text.length), // Keep cursor at the right position
                    )
                },
                keyboardActions = KeyboardActions(
                    onDone = {
                        if (descriptionState.text.isNotEmpty()) {
                            keyboardController?.hide()
                            focusManager.clearFocus()
                        }
                    },
                ),
                minLines = 4,
                maxLines = 4,
                singleLine = false,
                keyboardOptions = KeyboardOptions(
                    showKeyboardOnFocus = true,
                    imeAction = ImeAction.Done,
                )
            )
        }

        InfoSection(
            title = "Priority", modifier = Modifier.constrainAs(prioritySelector) {
                top.linkTo(tiDescription.bottom, margin = margin14)
                start.linkTo(parent.start, margin = margin14)
                end.linkTo(parent.end, margin = margin14)
                width = Dimension.fillToConstraints
            }
        ) {
            PrioritySelector(selectedPriority = selectedPriority) {
                selectedPriority = it
            }
        }

        InfoSection(
            title = "Due Date", modifier = Modifier.constrainAs(datePicker) {
                top.linkTo(prioritySelector.bottom, margin = margin14)
                start.linkTo(parent.start, margin = margin14)
                end.linkTo(parent.end, margin = margin14)
                width = Dimension.fillToConstraints
            }
        ) {
            PickerOpeningInput(
                modifier = Modifier.fillMaxWidth(),
                value = datePickerState.formattedDateStringOrPlaceholder(
                    stringResource(
                        R.string.due_date,
                    ),
                ),
                textColor = globalBlack,
                leadingIcon = ImageVector.vectorResource(id = R.drawable.ic_calendar),
                showLeadingIcon = true,
                onClick = {
                    showDatePicker = true
                },
            )
        }

        if (taskState.value != null){
            InfoSection(
                title = "Task Completed", modifier = Modifier.constrainAs(complete) {
                    top.linkTo(datePicker.bottom, margin = margin14)
                    start.linkTo(parent.start, margin = margin14)
                    end.linkTo(parent.end, margin = margin14)
                    width = Dimension.fillToConstraints
                }
            ) {
                Option(
                    title = stringResource(R.string.is_task_completed),
                    checked = isCompletedState,
                    onCheckedChange = {
                        isCompletedState = it
                    },
                )
            }
        }

        BrandedButton(
            modifier = Modifier.constrainAs(btnAddTask) {
                start.linkTo(parent.start, margin = margin14)
                end.linkTo(parent.end, margin = margin14)
                bottom.linkTo(parent.bottom, margin = margin14)
                width = Dimension.fillToConstraints
            },
            text = if (taskState.value != null) stringResource(R.string.update_task) else stringResource(R.string.add_task),
            onClick = {
                if (taskState.value != null) {
                    viewModel.updateTask(
                        TaskEntity(
                            id = viewModel.args.id,
                            title = titleState.text,
                            description = descriptionState.text,
                            priority = selectedPriority.name,
                            dueDate = datePickerState.selectedDateMillis ?: System.currentTimeMillis(),
                            isCompleted = isCompletedState
                        )
                    )
                } else {
                    viewModel.addTask(
                        TaskEntity(
                            title = titleState.text,
                            description = descriptionState.text,
                            priority = selectedPriority.name,
                            dueDate = datePickerState.selectedDateMillis ?: System.currentTimeMillis()
                        )
                    )
                }
                onTaskAdded()
            },
            enabled = titleState.text.isNotEmpty() && datePickerState.selectedDateMillis != null,
        )
    }

    if (showDatePicker) {
        DatePickerDialog(onDismissRequest = { showDatePicker = false }, confirmButton = {
            TextButton(
                onClick = {
                    showDatePicker = false
                },
                enabled = datePickerState.selectedDateMillis != null,
            ) {
                Text(
                    text = stringResource(R.string.global_confirm),
                    color = TypeTheme.colors.textPrimary,
                )
            }
        }, colors = DatePickerDefaults.taskColors(), dismissButton = {
            TextButton(onClick = { showDatePicker = false }) {
                Text(
                    text = stringResource(R.string.global_dismiss),
                    color = TypeTheme.colors.textPrimary,
                )
            }
        }, properties = DialogProperties(usePlatformDefaultWidth = true)
        ) {
            DatePicker(
                showModeToggle = false,
                state = datePickerState,
                colors = DatePickerDefaults.taskColors()
            )
        }
    }
}

@Composable
fun Option(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            style = TypeTheme.typography.bodyMediumBold,
            color = TypeTheme.colors.textPrimary,
        )
        CustomSwitch(
            isChecked = checked,
            onCheckedChange = onCheckedChange,
        )
    }
}

@Preview
@Composable
private fun HomePreview() {
    TaskManagerTheme(isSystemInDarkTheme()) {
        AddTaskScreen(onTaskAdded = {})
    }
}