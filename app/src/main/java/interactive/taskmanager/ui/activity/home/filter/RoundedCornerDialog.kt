package interactive.taskmanager.ui.activity.home.filter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.intuit.sdp.R
import interactive.taskmanager.ui.theme.TypeTheme
import interactive.taskmanager.ui.theme.color.LightBlue
import interactive.taskmanager.ui.theme.color.globalBlue
import interactive.taskmanager.ui.viewModel.TaskFilter
import interactive.taskmanager.ui.viewModel.TaskSort

@Composable
fun RoundedCornerDialog(
    selectedSort: TaskSort,
    selectedFilter: TaskFilter,
    onSortSelected: (TaskSort) -> Unit,
    onFilterSelected: (TaskFilter) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        val sp15 = dimensionResource(id = com.intuit.ssp.R.dimen._15ssp).value.sp
        val sp13 = dimensionResource(id = com.intuit.ssp.R.dimen._13ssp).value.sp
        val sp12 = dimensionResource(id = com.intuit.ssp.R.dimen._12ssp).value.sp
        val sp11 = dimensionResource(id = com.intuit.ssp.R.dimen._11ssp).value.sp

        val margin8 = dimensionResource(id = R.dimen._8sdp)
        val margin10 = dimensionResource(id = R.dimen._10sdp)
        val margin12 = dimensionResource(id = R.dimen._12sdp)
        val margin15 = dimensionResource(id = R.dimen._15sdp)
        val margin20 = dimensionResource(id = R.dimen._20sdp)

        Surface(
            shape = RoundedCornerShape(margin12),
            tonalElevation = margin8,
            color = TypeTheme.colors.backgroundSecondary,
            modifier = Modifier
                .padding(margin15)
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    "Sort & Filter",
                    style = TypeTheme.typography.bodySmallSemiBold.copy(fontSize = sp15),
                    color = TypeTheme.colors.textPrimary
                )

                Spacer(modifier = Modifier.height(margin15))

                Text(
                    "Sort by",
                    style = TypeTheme.typography.bodySmallSemiBold.copy(fontSize = sp13),
                    color = TypeTheme.colors.textPrimary
                )

                TaskSort.entries.forEach { sort ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clickable { onSortSelected(sort) },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = sort == selectedSort,
                            colors = RadioButtonDefaults.colors().copy(
                                selectedColor = globalBlue,
                                unselectedColor = LightBlue,
                                disabledSelectedColor = globalBlue,
                                disabledUnselectedColor = LightBlue
                            ),
                            onClick = { onSortSelected(sort) }
                        )
                        Spacer(Modifier.width(8.dp))
                        when (sort.name) {
                            TaskSort.PRIORITY.name -> {
                                Text(
                                    "Priority",
                                    style = TypeTheme.typography.bodySmallSemiBold.copy(fontSize = sp12),
                                    color = TypeTheme.colors.textPrimary
                                )
                            }

                            TaskSort.DUE_DATE.name -> {
                                Text(
                                    "Due Date",
                                    style = TypeTheme.typography.bodySmallSemiBold.copy(fontSize = sp12),
                                    color = TypeTheme.colors.textPrimary
                                )
                            }

                            TaskSort.TITLE.name -> {
                                Text(
                                    "Title",
                                    style = TypeTheme.typography.bodySmallSemiBold.copy(fontSize = sp12),
                                    color = TypeTheme.colors.textPrimary
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(margin15))

                Text(
                    "Filter by",
                    style = TypeTheme.typography.bodySmallSemiBold.copy(fontSize = sp13),
                    color = TypeTheme.colors.textPrimary
                )

                TaskFilter.entries.forEach { filter ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clickable { onFilterSelected(filter) },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = filter == selectedFilter,
                            colors = RadioButtonDefaults.colors().copy(
                                selectedColor = globalBlue,
                                unselectedColor = LightBlue,
                                disabledSelectedColor = globalBlue,
                                disabledUnselectedColor = LightBlue
                            ),
                            onClick = { onFilterSelected(filter) }
                        )
                        Spacer(Modifier.width(8.dp))
                        when (filter.name) {
                            TaskFilter.ALL.name -> {
                                Text(
                                    "All",
                                    style = TypeTheme.typography.bodySmallSemiBold.copy(fontSize = sp12),
                                    color = TypeTheme.colors.textPrimary
                                )
                            }

                            TaskFilter.COMPLETED.name -> {
                                Text(
                                    "Completed",
                                    style = TypeTheme.typography.bodySmallSemiBold.copy(fontSize = sp12),
                                    color = TypeTheme.colors.textPrimary
                                )
                            }

                            TaskFilter.PENDING.name -> {
                                Text(
                                    "Pending",
                                    style = TypeTheme.typography.bodySmallSemiBold.copy(fontSize = sp12),
                                    color = TypeTheme.colors.textPrimary
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(margin20))
                Button(
                    onClick = onDismiss,
                    modifier = Modifier.align(Alignment.End),
                    colors = ButtonDefaults.buttonColors().copy(
                        containerColor = globalBlue,
                        contentColor = TypeTheme.colors.textPrimary
                    )
                ) {
                    Text(
                        "Done",
                        style = TypeTheme.typography.bodySmallSemiBold.copy(fontSize = sp12)
                    )
                }
            }
        }
    }
}
