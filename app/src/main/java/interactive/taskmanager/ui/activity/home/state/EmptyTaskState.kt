package interactive.taskmanager.ui.activity.home.state

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import interactive.taskmanager.R
import interactive.taskmanager.ui.theme.TypeTheme
import interactive.taskmanager.ui.theme.color.globalBlue

@Composable
fun EmptyTaskState(
    modifier: Modifier = Modifier,
    onAddTaskClick: (() -> Unit)? = null
) {
    val margin8 = dimensionResource(id = com.intuit.sdp.R.dimen._8sdp)
    val margin10 = dimensionResource(id = com.intuit.sdp.R.dimen._10sdp)
    val margin15 = dimensionResource(id = com.intuit.sdp.R.dimen._15sdp)
    val margin30 = dimensionResource(id = com.intuit.sdp.R.dimen._30sdp)
    val margin100 = dimensionResource(id = com.intuit.sdp.R.dimen._100sdp)

    val sp18 = dimensionResource(id = com.intuit.ssp.R.dimen._18ssp).value.sp
    val sp16 = dimensionResource(id = com.intuit.ssp.R.dimen._16ssp).value.sp
    val sp14 = dimensionResource(id = com.intuit.ssp.R.dimen._14ssp).value.sp
    val sp13 = dimensionResource(id = com.intuit.ssp.R.dimen._13ssp).value.sp
    val sp12 = dimensionResource(id = com.intuit.ssp.R.dimen._12ssp).value.sp

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(margin30),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Illustration
        Image(
            painter = painterResource(id = R.drawable.ic_no_task), // replace with your illustration
            contentDescription = "No tasks",
            colorFilter = ColorFilter.tint(TypeTheme.colors.textPrimary),
            modifier = Modifier
                .size(margin100)
        )

        // Motivational Message
        Text(
            text = "No tasks yet!",
            style = TypeTheme.typography.bodySmallSemiBold.copy(fontSize = sp18),
            color = TypeTheme.colors.textPrimary
        )

        Spacer(modifier = Modifier.height(margin10))

        Text(
            text = "Every great achievement starts with a plan.Add your first task and get started!",
            style = TypeTheme.typography.bodySmall.copy(fontSize = sp13, lineHeight = sp16),
            color = TypeTheme.colors.textSecondary,
            textAlign = TextAlign.Center
        )

        if (onAddTaskClick != null) {
            Spacer(modifier = Modifier.height(margin15))
            Button(
                onClick = onAddTaskClick,
                colors = ButtonDefaults.buttonColors().copy(
                    containerColor = globalBlue,
                    contentColor = TypeTheme.colors.textPrimary
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = margin8)
            ) {
                Text(
                    "Add Task",
                    style = TypeTheme.typography.bodySmallSemiBold.copy(fontSize = sp12)
                )
            }
        }
    }
}
