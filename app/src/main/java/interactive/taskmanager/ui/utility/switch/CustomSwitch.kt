package interactive.taskmanager.ui.utility.switch

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import interactive.taskmanager.ui.theme.TaskManagerTheme
import interactive.taskmanager.ui.theme.color.globalGreen
import interactive.taskmanager.ui.theme.color.lineMidLight

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun CustomSwitch(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    var checked by remember { mutableStateOf(isChecked) }

    // Animate background color change
    val switchColor by animateColorAsState(
        targetValue = if (checked) globalGreen else lineMidLight,
        label = "switchColorAnimation",
    )

    // Animate thumb position
    val thumbOffset by animateDpAsState(
        targetValue = if (checked) 26.dp else 2.dp,
        label = "thumbAnimation",
    )

    Box(
        modifier = Modifier
            .width(50.dp)
            .height(26.dp)
            .clip(RoundedCornerShape(35))
            .background(switchColor)
            .clickable {
                checked = !checked
                onCheckedChange(checked)
            },
        contentAlignment = Alignment.CenterStart,
    ) {
        Box(
            modifier = Modifier
                .size(22.dp)
                .offset(x = thumbOffset)
                .clip(RoundedCornerShape(35))
                .background(Color.White),
        )
    }
}

@Preview
@Composable
private fun Switch_Unchecked_Preview() {
    TaskManagerTheme(isSystemInDarkTheme()) {
        CustomSwitch(
            isChecked = true,
            onCheckedChange = { },
        )
    }
}
