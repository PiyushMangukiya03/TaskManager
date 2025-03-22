package interactive.taskmanager.ui.activity.setting

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import interactive.taskmanager.R
import interactive.taskmanager.ui.activity.addTask.Option
import interactive.taskmanager.ui.repository.AppThemeMode
import interactive.taskmanager.ui.utility.forms.InfoSection
import interactive.taskmanager.ui.utility.toolBar.ToolBar
import interactive.taskmanager.ui.viewModel.ThemeViewModel

@Composable
internal fun SettingScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ThemeViewModel = hiltViewModel()
) {
    val themeMode by viewModel.themeMode.collectAsState()
    val margin15 = dimensionResource(id = com.intuit.sdp.R.dimen._15sdp)

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
    ) {
        val (toolBar, themeSelector) = createRefs()

        ToolBar(onBackClick,
            title = stringResource(R.string.setting),
            modifier = Modifier.constrainAs(toolBar) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

        InfoSection(
            title = "Choose Theme", modifier = Modifier.constrainAs(themeSelector) {
                top.linkTo(toolBar.bottom, margin = margin15)
                start.linkTo(parent.start, margin = margin15)
                end.linkTo(parent.end, margin = margin15)
                width = Dimension.fillToConstraints
            }
        ) {
            Option(
                title = if (themeMode == AppThemeMode.DARK) "Switch to Light" else "Switch to Dark",
                checked = themeMode == AppThemeMode.DARK,
                onCheckedChange = {
                    if (it){
                        viewModel.setTheme(AppThemeMode.DARK)
                    } else {
                        viewModel.setTheme(AppThemeMode.LIGHT)
                    }
                },
            )
        }
    }
}