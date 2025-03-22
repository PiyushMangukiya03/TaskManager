package interactive.taskmanager.ui.utility.extension

import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import interactive.taskmanager.ui.theme.TypeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDefaults.taskColors() = colors().copy(
    containerColor = TypeTheme.colors.backgroundSecondary,
    titleContentColor = TypeTheme.colors.textPrimary,
    headlineContentColor = TypeTheme.colors.textPrimary,
    weekdayContentColor = TypeTheme.colors.textPrimary,
    subheadContentColor = TypeTheme.colors.textPrimary,
    navigationContentColor = TypeTheme.colors.textPrimary,
    yearContentColor = TypeTheme.colors.textPrimary,
    currentYearContentColor = TypeTheme.colors.textPrimary,
    selectedYearContentColor = TypeTheme.colors.colorPrimary,
    selectedYearContainerColor = TypeTheme.colors.colorAccent,
    dayContentColor = TypeTheme.colors.textPrimary,
    selectedDayContentColor = TypeTheme.colors.colorPrimary,
    selectedDayContainerColor = TypeTheme.colors.colorAccent,
    todayContentColor = TypeTheme.colors.textPrimary,
    todayDateBorderColor = TypeTheme.colors.colorAccent,
    dayInSelectionRangeContentColor = TypeTheme.colors.textPrimary,
    dayInSelectionRangeContainerColor = TypeTheme.colors.textPrimary,
    dividerColor = TypeTheme.colors.colorAccent,
    disabledYearContentColor = TypeTheme.colors.textTertiary,
    disabledDayContentColor = TypeTheme.colors.textTertiary
)
