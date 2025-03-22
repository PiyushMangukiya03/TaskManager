package interactive.taskmanager.ui.utility.picker

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import interactive.taskmanager.R
import interactive.taskmanager.ui.theme.TaskManagerTheme
import interactive.taskmanager.ui.theme.TypeTheme
import interactive.taskmanager.ui.theme.color.LightBlue
import interactive.taskmanager.ui.theme.color.globalBlack

@Composable
internal fun PickerOpeningInput(
    onClick: () -> Unit,
    value: String,
    textColor: Color,
    trailingIcon: ImageVector? = null,
    showTrailingIcon: Boolean = false,
    leadingIcon: ImageVector? = null,
    showLeadingIcon: Boolean = false,
    modifier: Modifier = Modifier,
) {
    val margin8 = dimensionResource(id = com.intuit.sdp.R.dimen._8sdp)

    ConstraintLayout(
        modifier = modifier
            .clip(RoundedCornerShape(dimensionResource(id = com.intuit.sdp.R.dimen._12sdp)))
            .background(LightBlue)
            .clickable { onClick.invoke() }
            .padding(dimensionResource(id = com.intuit.sdp.R.dimen._14sdp)),
    ) {
        val (textFieldRef, leadingIconRef, trailingContent) = createRefs()

        if (showLeadingIcon) {
            leadingIcon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    tint = globalBlack,
                    modifier = Modifier
                        .constrainAs(leadingIconRef) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        },
                )
            }
        }

        Text(
            text = value,
            style = TypeTheme.typography.bodyMediumSemiBold,
            color = textColor,
            modifier = Modifier
                .constrainAs(textFieldRef) {
                    start.linkTo(leadingIconRef.end, margin = if (showLeadingIcon) margin8 else 0.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(trailingContent.start)
                    width = Dimension.fillToConstraints
                },
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.constrainAs(trailingContent) {
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            },
        ) {
            if (showTrailingIcon) {
                trailingIcon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = null,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun EventInfoPage_PhysicalEvent_Preview() {
    TaskManagerTheme(isSystemInDarkTheme()) {
        PickerOpeningInput(
            modifier = Modifier.fillMaxWidth(),
            value = "MM/DD/YYYY",
            textColor = globalBlack,
            leadingIcon = ImageVector.vectorResource(id = R.drawable.ic_calendar),
            showLeadingIcon = true,
            trailingIcon = ImageVector.vectorResource(id = R.drawable.ic_calendar),
            showTrailingIcon = true,
            onClick = {},
        )
    }
}
