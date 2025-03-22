package interactive.taskmanager.ui.activity.addTask

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import interactive.taskmanager.ui.theme.color.globalBlack
import interactive.taskmanager.ui.theme.color.globalWhite
import interactive.taskmanager.ui.theme.color.green
import interactive.taskmanager.ui.theme.color.red
import interactive.taskmanager.ui.theme.color.yellow

@Composable
fun PrioritySelector(
    selectedPriority: Priority, // Current selected priority
    modifier: Modifier = Modifier,
    onPrioritySelected: (Priority) -> Unit // Callback when a priority is selected
) {
    val priorities = listOf(
        Priority.Low to Icons.Default.ArrowDownward,
        Priority.Medium to Icons.Default.Remove,
        Priority.High to Icons.Default.ArrowUpward
    )

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        priorities.forEachIndexed { index, (priority, icon) ->
            val isSelected = selectedPriority == priority
            val backgroundColor by animateColorAsState(
                targetValue = if (isSelected) priority.color else MaterialTheme.colorScheme.surface,
                animationSpec = tween(300),
                label = "Priority Color Animation"
            )

            val textColor by animateColorAsState(
                targetValue = if (isSelected) globalWhite else globalBlack,
                animationSpec = tween(300),
                label = "Priority Text Color Animation"
            )

            val iconTint by animateColorAsState(
                targetValue = if (isSelected) globalWhite else globalBlack,
                animationSpec = tween(300),
                label = "Priority Icon Tint Animation"
            )

            Card(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = if (index == 0) 0.dp else dimensionResource(id = com.intuit.sdp.R.dimen._4sdp),
                        end = if (index == priorities.lastIndex) 0.dp else dimensionResource(id = com.intuit.sdp.R.dimen._4sdp)
                    )
                    .clickable { onPrioritySelected(priority) },
                shape = RoundedCornerShape(dimensionResource(id = com.intuit.sdp.R.dimen._12sdp)),
                elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(id = com.intuit.sdp.R.dimen._6sdp)),
                colors = CardDefaults.cardColors(containerColor = backgroundColor)
            ) {
                Column(
                    modifier = Modifier
                        .padding(dimensionResource(id = com.intuit.sdp.R.dimen._14sdp))
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(imageVector = icon, contentDescription = priority.name, tint = iconTint)
                    Spacer(modifier = Modifier.height(dimensionResource(id = com.intuit.sdp.R.dimen._2sdp)))
                    Text(
                        text = priority.name,
                        style = MaterialTheme.typography.labelLarge,
                        color = textColor
                    )
                }
            }
        }
    }
}

// Enum class for Priority
enum class Priority(val color: Color) {
    Low(green),    // Green
    Medium(yellow), // Amber
    High(red)    // Red
}

// Preview function
@Preview(showBackground = true)
@Composable
fun PreviewPrioritySelector() {
    var selectedPriority by remember { mutableStateOf(Priority.Medium) }

    PrioritySelector(selectedPriority = selectedPriority) {
        selectedPriority = it
    }
}
