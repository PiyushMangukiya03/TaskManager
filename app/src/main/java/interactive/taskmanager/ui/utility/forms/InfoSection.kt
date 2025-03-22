package interactive.taskmanager.ui.utility.forms

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import interactive.taskmanager.ui.theme.TaskManagerTheme
import interactive.taskmanager.ui.theme.TypeTheme

@Composable
fun InfoSection(
    title: String,
    modifier: Modifier = Modifier,
    titleHorizontalPadding: Dp = 0.dp,
    titleSpacerHeight: Dp = 8.dp,
    content: @Composable () -> Unit,
) {
    val sp10 = dimensionResource(id = com.intuit.ssp.R.dimen._10ssp).value.sp
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(horizontal = titleHorizontalPadding),
            text = title.uppercase(),
            style = TypeTheme.typography.titleMedium.copy(fontSize = sp10),
            color = TypeTheme.colors.textPrimary,
        )
        Spacer(modifier = Modifier.height(titleSpacerHeight))
        content()
    }
}

@Preview
@Composable
private fun InfoSection_Preview() {
    TaskManagerTheme(isSystemInDarkTheme()) {
        InfoSection(
            title = "Example section",
            content = {
                Text("Some content")
            },
        )
    }
}
