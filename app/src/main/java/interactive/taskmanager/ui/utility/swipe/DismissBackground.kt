package interactive.taskmanager.ui.utility.swipe

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import interactive.taskmanager.R
import interactive.taskmanager.ui.theme.color.globalBlack

@Composable
fun DismissBackground(taskColor: Color) {

    val margin15 = dimensionResource(id = com.intuit.sdp.R.dimen._15sdp)
    val size = dimensionResource(id = com.intuit.sdp.R.dimen._20sdp)

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (startDivider, delete, divider, done, endDivider) = createRefs()

        HorizontalDivider(
            thickness = dimensionResource(id = com.intuit.sdp.R.dimen._1sdp),
            color = taskColor,
            modifier = Modifier
                .constrainAs(startDivider) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(delete.start)
                    width = Dimension.fillToConstraints
                }
        )

        Icon(
            painter = painterResource(R.drawable.ic_delete),
            contentDescription = "delete",
            tint = taskColor,
            modifier = Modifier
                .size(size)
                .constrainAs(delete) {
                    start.linkTo(parent.start, margin = margin15)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )

        HorizontalDivider(
            thickness = dimensionResource(id = com.intuit.sdp.R.dimen._1sdp),
            color = taskColor,
            modifier = Modifier
                .constrainAs(divider) {
                    start.linkTo(delete.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(done.start)
                    width = Dimension.fillToConstraints
                }
        )

        Icon(
            painter = painterResource(R.drawable.ic_done),
            contentDescription = "Archive",
            tint = taskColor,
            modifier = Modifier
                .size(size)
                .constrainAs(done) {
                    end.linkTo(parent.end, margin = margin15)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )

        HorizontalDivider(
            thickness = dimensionResource(id = com.intuit.sdp.R.dimen._1sdp),
            color = taskColor,
            modifier = Modifier
                .constrainAs(endDivider) {
                    start.linkTo(done.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
        )
    }
}

// Preview function
@Preview(showBackground = true)
@Composable
fun PreviewDismissBackground() {
    DismissBackground(globalBlack)
}