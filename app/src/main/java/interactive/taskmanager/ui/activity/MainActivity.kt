package interactive.taskmanager.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import interactive.taskmanager.di.entity.TaskEntity
import interactive.taskmanager.ui.activity.addTask.addTask
import interactive.taskmanager.ui.activity.addTask.navigateToAddTask
import interactive.taskmanager.ui.activity.home.HomePage
import interactive.taskmanager.ui.activity.home.HomeScreen
import interactive.taskmanager.ui.activity.home.TaskComponent
import interactive.taskmanager.ui.activity.home.homePage
import interactive.taskmanager.ui.activity.setting.navigateToSetting
import interactive.taskmanager.ui.activity.setting.settingPage
import interactive.taskmanager.ui.repository.AppThemeMode
import interactive.taskmanager.ui.theme.TaskManagerTheme
import interactive.taskmanager.ui.theme.TypeTheme
import interactive.taskmanager.ui.viewModel.ThemeViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val themeViewModel: ThemeViewModel by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeMode by themeViewModel.themeMode.collectAsState()

            val isDarkTheme = when (themeMode) {
                AppThemeMode.LIGHT -> false
                AppThemeMode.DARK -> true
                AppThemeMode.SYSTEM -> isSystemInDarkTheme()
            }
            TaskManagerTheme(darkTheme = isDarkTheme) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = TypeTheme.colors.backgroundPrimary
                ) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        navController = navController,
                        startDestination = HomePage,
                    ) {
                        homePage(
                            onSetting = navController::navigateToSetting,
                            onAddTask = navController::navigateToAddTask,
                            onDetails = {
                                navController.navigateToAddTask(it)
                            }
                        )

                        addTask {
                            navController.popBackStack()
                        }

                        settingPage {
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TaskManagerTheme(isSystemInDarkTheme()) {
        HomeScreen(
            onSetting = {},
            onAddTask = {},
            onDetails = {}
        )
    }
}