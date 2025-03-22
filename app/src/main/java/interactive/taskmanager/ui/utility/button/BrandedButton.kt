package interactive.taskmanager.ui.utility.button

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button as Material3Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import interactive.taskmanager.ui.preview.Preview
import interactive.taskmanager.ui.theme.TypeTheme
import interactive.taskmanager.ui.theme.actionGradient
import interactive.taskmanager.utils.UiModePreviews

@Composable
fun BrandedButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    progress: Boolean = false,
    enabledGradient: Brush = actionGradient,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = Color.Transparent,
        disabledContainerColor = Color.Transparent,
        contentColor = TypeTheme.colors.colorPrimary,
        disabledContentColor = Color(0xFF737780),
    ),
    background: Brush = if (enabled && progress.not()) {
        enabledGradient
    } else {
        SolidColor(
            Color(
                0xFFEEF0F2,
            ),
        )
    },
) {
    Material3Button(
        onClick = onClick,
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(
                background,
            ),
        enabled = enabled,
        shape = RoundedCornerShape(12.dp),
        colors = colors,
        elevation = ButtonDefaults.buttonElevation(),
        border = null,
        contentPadding = ButtonDefaults.ContentPadding,
        interactionSource = interactionSource,
    ) {
        val textContent: @Composable RowScope.() -> Unit = {
            Text(
                text = text,
                style = TypeTheme.typography.bodyMediumBold,
                color = TypeTheme.colors.textPrimary
            )
        }

        val buttonContent: @Composable RowScope.() -> Unit = if (progress) {
            {
                Box(
                    contentAlignment = Alignment.Center,
                ) {
                    Row(modifier = Modifier.alpha(0f)) { textContent() }
                }
            }
        } else {
            textContent
        }
        AnimatedContent(targetState = buttonContent, label = "") {
            Row(
                modifier = Modifier
                    .padding(PaddingValues(vertical = 8.dp)),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                content = it,
            )
        }
    }
}

@UiModePreviews
@Composable
internal fun BrandedButton_Preview() {
    Preview {
        BrandedButton(
            onClick = {},
            text = "Branded button",
        )
    }
}
