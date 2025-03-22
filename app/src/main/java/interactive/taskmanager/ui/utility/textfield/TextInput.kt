package interactive.taskmanager.ui.utility.textfield

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.intuit.sdp.R
import interactive.taskmanager.ui.theme.TypeTheme
import interactive.taskmanager.ui.theme.color.LightBlue
import interactive.taskmanager.ui.theme.color.globalBlack
import interactive.taskmanager.ui.theme.color.globalWhite

@Composable
fun TextInput(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    val sp16 = dimensionResource(id = com.intuit.ssp.R.dimen._16ssp).value.sp
    TextField(
        modifier = modifier,
        value = value,
        singleLine = singleLine,
        minLines = minLines,
        maxLines = maxLines,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                style = TypeTheme.typography.bodyMediumSemiBold.copy(lineHeight = sp16),
                color = globalBlack
            )
        },
        leadingIcon = null,
        trailingIcon = null,
        textColor = globalBlack,
        colors = TextFieldDefaults.colors().copy(
            focusedContainerColor = LightBlue,
            unfocusedContainerColor = LightBlue,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = globalWhite,
            cursorColor = globalBlack,
            unfocusedPlaceholderColor = TypeTheme.colors.textTertiary,
            focusedPlaceholderColor = TypeTheme.colors.textTertiary,
            textSelectionColors = TextSelectionColors(
                handleColor = TypeTheme.colors.colorAccent,
                backgroundColor = TypeTheme.colors.error,
            ),
        ),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen._12sdp)),
        contentPadding = PaddingValues(dimensionResource(id = R.dimen._14sdp)),
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
    )
}
