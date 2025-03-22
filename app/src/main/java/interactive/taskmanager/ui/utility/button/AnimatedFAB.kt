package interactive.taskmanager.ui.utility.button

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import interactive.taskmanager.ui.theme.TypeTheme
import interactive.taskmanager.ui.theme.color.globalBlue
import kotlinx.coroutines.delay

@Composable
fun AnimatedFAB(modifier: Modifier,onClick: () -> Unit) {
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 1.2f else 1f,
        animationSpec = tween(durationMillis = 150, easing = FastOutSlowInEasing),
        label = "FAB Scale Animation"
    )

    FloatingActionButton(
        onClick = {
            isPressed = true
        },
        modifier = modifier
            .scale(scale),
        containerColor = globalBlue
    ) {
        Icon(Icons.Default.Add, contentDescription = "Add Task", tint = TypeTheme.colors.backgroundPrimary)
    }

    // Reset scale after animation delay
    LaunchedEffect(isPressed) {
        if (isPressed) {
            delay(150) // Wait for animation to finish
            onClick()
            isPressed = false
        }
    }
}
