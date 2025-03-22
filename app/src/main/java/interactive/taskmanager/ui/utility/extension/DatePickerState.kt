package interactive.taskmanager.ui.utility.extension

import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import interactive.taskmanager.utils.toFormattedString
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerState.formattedDateStringOrPlaceholder(placeholder: String, pattern: String = "MM/dd/yyyy"): String {
    val mills = this.selectedDateMillis
    return if (mills == null) {
        placeholder
    } else {
        val instant = Instant.ofEpochMilli(mills)
        val date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        date.toFormattedString(pattern)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
fun DatePickerState.selectedLocalDate(): LocalDateTime? {
    val mills = this.selectedDateMillis
    return if (mills == null) {
        null
    } else {
        val instant = Instant.ofEpochMilli(mills)
        LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    }
}

fun Long?.toFormattedString(pattern: String): String {
    return if (this == null) {
        ""
    } else {
        val instant = Instant.ofEpochMilli(this)
        val date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        date.toFormattedString(pattern)
    }
}
