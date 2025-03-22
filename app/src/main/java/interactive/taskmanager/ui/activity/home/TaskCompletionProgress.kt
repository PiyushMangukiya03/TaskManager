package interactive.taskmanager.ui.activity.home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.intuit.sdp.R
import interactive.taskmanager.ui.theme.TypeTheme
import interactive.taskmanager.ui.theme.color.LightBlue
import interactive.taskmanager.ui.theme.color.globalBlue
import interactive.taskmanager.ui.utility.indicator.IndicatorItemUI

@Composable
fun TaskCompletionProgress(
    completedTasks: Int,
    totalTasks: Int,
    modifier: Modifier = Modifier
) {
    val percentage = if (totalTasks > 0) completedTasks.toFloat() / totalTasks else 0f
    val animatedProgress by animateFloatAsState(
        targetValue = percentage,
        label = "AnimatedProgress"
    )
    val margin10 = dimensionResource(id = R.dimen._10sdp)

    ConstraintLayout(modifier = modifier) {
        val (progress, tvProgress, indFinish, indPending) = createRefs()

        CircularProgressIndicator(
            progress = { animatedProgress },
            modifier = Modifier.size(dimensionResource(id = com.intuit.sdp.R.dimen._90sdp)).constrainAs(progress){
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            },
            color = TypeTheme.colors.colorSecondary,
            strokeWidth = dimensionResource(id = com.intuit.sdp.R.dimen._10sdp),
            trackColor = LightBlue,
            gapSize = dimensionResource(id = com.intuit.sdp.R.dimen._minus5sdp)
        )

        Text(
            text = "${(percentage * 100).toInt()}%\nDone",
            style = TypeTheme.typography.bodyMediumSemiBold.copy(textAlign = TextAlign.Center),
            color = TypeTheme.colors.textPrimary,
            modifier = Modifier.constrainAs(tvProgress){
                top.linkTo(progress.top)
                start.linkTo(progress.start)
                end.linkTo(progress.end)
                bottom.linkTo(progress.bottom)
            }
        )

        IndicatorItemUI(
            color = globalBlue,
            text = "Finish on time",
            modifier = Modifier.constrainAs(indFinish) {
                top.linkTo(progress.top, margin = margin10)
                start.linkTo(progress.end)
                end.linkTo(parent.end)
                width = Dimension.preferredWrapContent
            }
        )

        IndicatorItemUI(
            color = LightBlue,
            text = "Still ongoing",
            modifier = Modifier.constrainAs(indPending) {
                top.linkTo(indFinish.bottom, margin = margin10)
                start.linkTo(indFinish.start)
                width = Dimension.preferredWrapContent
            }
        )
    }
}
