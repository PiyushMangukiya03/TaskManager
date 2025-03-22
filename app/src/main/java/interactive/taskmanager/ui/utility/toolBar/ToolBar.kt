package interactive.taskmanager.ui.utility.toolBar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import interactive.taskmanager.ui.theme.TypeTheme

@Composable
internal fun ToolBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    title: String
) {
    val sp16 = dimensionResource(id = com.intuit.ssp.R.dimen._16ssp).value.sp

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
    ) {
        val (imgBack, tvTitle) = createRefs()

        IconButton(
            onClick = {
                onBackClick()
            },
            modifier = Modifier.constrainAs(imgBack) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                width = Dimension.wrapContent
            }
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowBackIosNew,
                contentDescription = "back",
                tint = TypeTheme.colors.textPrimary
            )
        }

        Text(
            text = title,
            color = TypeTheme.colors.textPrimary,
            style = TypeTheme.typography.bodySmallBold.copy(fontSize = sp16),
            modifier = Modifier.constrainAs(tvTitle) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        )
    }
}