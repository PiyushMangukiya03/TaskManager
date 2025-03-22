package interactive.taskmanager.ui.utility.textfield

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextFieldDefaults.ContainerBox
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import interactive.taskmanager.ui.theme.TaskManagerTheme
import interactive.taskmanager.ui.theme.TypeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    textColor: Color = TypeTheme.colors.colorPrimary,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    maxCharCount: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = TextFieldDefaults.shape,
    colors: TextFieldColors = TextFieldDefaults.colors(),
    contentPadding: PaddingValues = PaddingValues(8.dp),
) {
    CompositionLocalProvider(
        LocalTextSelectionColors provides TextSelectionColors(
            handleColor = TypeTheme.colors.colorAccent,
            backgroundColor = TypeTheme.colors.colorAccent.copy(alpha = 0.2f),
        ),
    ) {
        BasicTextField(
            value = value,
            onValueChange = { if (it.text.length <= maxCharCount) onValueChange(it) },
            modifier = modifier
                .animateContentSize(),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            interactionSource = interactionSource,
            singleLine = singleLine,
            readOnly = readOnly,
            cursorBrush = SolidColor(colors.cursorColor),
            textStyle = textStyle.copy(color = textColor),
            visualTransformation = visualTransformation,
            onTextLayout = {},
            maxLines = maxLines,
            minLines = minLines,
            decorationBox = @Composable {
                TextFieldDefaults.DecorationBox(
                    value = value.text,
                    innerTextField = it,
                    enabled = enabled,
                    singleLine = true,
                    visualTransformation = visualTransformation,
                    interactionSource = interactionSource,
                    isError = false,
                    label = label,
                    placeholder = placeholder,
                    leadingIcon = leadingIcon,
                    trailingIcon = trailingIcon,
                    prefix = prefix,
                    suffix = suffix,
                    supportingText = supportingText,
                    shape = TextFieldDefaults.shape,
                    colors = colors,
                    contentPadding = contentPadding,
                    container = {
                        ContainerBox(enabled, isError, interactionSource, colors, shape)
                    },
                )
            },
        )
    }
}

@Preview
@Composable
private fun TextField_Preview() {
    TaskManagerTheme(isSystemInDarkTheme()) {
        TextField(
            value = TextFieldValue(),
            onValueChange = {},
            modifier = Modifier,
            enabled = false,
            readOnly = false,
            textStyle = TypeTheme.typography.bodyMedium,
            textColor = TypeTheme.colors.textPrimary,
            label = {},
            placeholder = {},
            leadingIcon = {},
            trailingIcon = {},
            prefix = {},
            suffix = {},
            supportingText = {},
            isError = false,
            visualTransformation = VisualTransformation.None,
            keyboardOptions = KeyboardOptions.Default,
            keyboardActions = KeyboardActions.Default,
            singleLine = false,
            maxLines = 1627,
            minLines = 9922,
            interactionSource = remember { MutableInteractionSource() },
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(),
            contentPadding = PaddingValues(),
        )
    }
}
