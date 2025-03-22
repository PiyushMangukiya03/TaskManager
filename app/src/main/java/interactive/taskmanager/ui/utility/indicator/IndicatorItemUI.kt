package interactive.taskmanager.ui.utility.indicator

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import interactive.taskmanager.R
import interactive.taskmanager.ui.theme.TypeTheme
import interactive.taskmanager.ui.theme.color.LightBlue

@Composable
fun IndicatorItemUI(color: Color = LightBlue, text:String, modifier: Modifier = Modifier) {

    val margin5 = dimensionResource(id = com.intuit.sdp.R.dimen._5sdp)
    val sp16 = dimensionResource(id = com.intuit.ssp.R.dimen._10ssp).value.sp

    ConstraintLayout(modifier = modifier) {
        val (progress, tvText) = createRefs()
        Icon(
            painter = painterResource(id = R.drawable.ic_circle),
            contentDescription = "",
            tint = color,
            modifier = Modifier.size(20.dp).constrainAs(progress){
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            }
        )

        Text(
            text = text,
            color = TypeTheme.colors.textPrimary,
            fontSize = sp16,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.constrainAs(tvText){
                top.linkTo(parent.top)
                start.linkTo(progress.end, margin = margin5)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            }
        )
    }
}