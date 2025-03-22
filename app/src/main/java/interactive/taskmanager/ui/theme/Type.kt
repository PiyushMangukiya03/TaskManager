package interactive.taskmanager.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import interactive.taskmanager.R
import interactive.taskmanager.ui.theme.Type.bodyLarge
import interactive.taskmanager.ui.theme.Type.bodyLargeBold
import interactive.taskmanager.ui.theme.Type.bodyLargeMedium
import interactive.taskmanager.ui.theme.Type.bodyMedium
import interactive.taskmanager.ui.theme.Type.bodyMediumBold
import interactive.taskmanager.ui.theme.Type.bodyMediumSemiBold
import interactive.taskmanager.ui.theme.Type.bodySmall
import interactive.taskmanager.ui.theme.Type.bodySmallBold
import interactive.taskmanager.ui.theme.Type.bodySmallSemiBold
import interactive.taskmanager.ui.theme.Type.bodyXSmall
import interactive.taskmanager.ui.theme.Type.bodyXSmallBold
import interactive.taskmanager.ui.theme.Type.bodyXSmallMedium
import interactive.taskmanager.ui.theme.Type.titleLarge
import interactive.taskmanager.ui.theme.Type.titleMedium
import interactive.taskmanager.ui.theme.Type.titleMediumBold
import interactive.taskmanager.ui.theme.Type.titleXLarge

val CustomFontFamily = FontFamily(
    Font(R.font.sf_pro_regular, FontWeight.Normal),
    Font(R.font.sf_pro_medium, FontWeight.Medium),
    Font(R.font.sf_pro_bold, FontWeight.Bold),
    Font(R.font.sf_pro_semibold, FontWeight.SemiBold),
)

@Suppress("LongParameterList")
@Immutable
class TaskTypography(
    val titleXLarge: TextStyle,
    val titleLarge: TextStyle,
    val titleMedium: TextStyle,
    val titleMediumBold: TextStyle,
    val bodyMedium: TextStyle,
    val bodyXSmall: TextStyle,
    val bodyXSmallBold: TextStyle,
    val bodyXSmallMedium: TextStyle,
    val bodyMediumBold: TextStyle,
    val bodyMediumSemiBold: TextStyle,
    val bodySmallBold: TextStyle,
    val bodySmallSemiBold: TextStyle,
    val bodySmall: TextStyle,
    val bodyLarge: TextStyle,
    val bodyLargeMedium: TextStyle,
    val bodyLargeBold: TextStyle,
)

internal object Type {
    val titleXLarge = TextStyle(
        fontFamily = CustomFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
    )

    val titleLarge = TextStyle(
        fontFamily = CustomFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 32.sp,
    )

    val titleMedium = TextStyle(
        fontFamily = CustomFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 24.sp,
    )

    val titleMediumBold = TextStyle(
        fontFamily = CustomFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 25.sp,
    )

    val bodyMediumBold = TextStyle(
        fontFamily = CustomFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
    )

    val bodyMediumSemiBold = TextStyle(
        fontFamily = CustomFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
    )

    val bodyMedium = TextStyle(
        fontFamily = CustomFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 20.sp,
    )

    val bodySmallBold = TextStyle(
        fontFamily = CustomFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 18.sp,
    )

    val bodySmallSemiBold = TextStyle(
        fontFamily = CustomFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 18.sp,
    )

    val bodySmall = TextStyle(
        fontFamily = CustomFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 18.sp,
    )

    val bodyXSmallBold = TextStyle(
        fontFamily = CustomFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    )

    val bodyXSmallMedium = TextStyle(
        fontFamily = CustomFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    )

    val bodyXSmall = TextStyle(
        fontFamily = CustomFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    )

    val bodyLarge = TextStyle(
        fontFamily = CustomFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 24.sp,
    )
    val bodyLargeMedium = TextStyle(
        fontFamily = CustomFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 24.sp,
    )
    val bodyLargeBold = TextStyle(
        fontFamily = CustomFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
    )
}

val TaskAppTypography = TaskTypography(
    titleXLarge = titleXLarge,
    titleLarge = titleLarge,
    titleMedium = titleMedium,
    titleMediumBold = titleMediumBold,
    bodyMedium = bodyMedium,
    bodyMediumBold = bodyMediumBold,
    bodySmall = bodySmall,
    bodyXSmall = bodyXSmall,
    bodySmallSemiBold = bodySmallSemiBold,
    bodySmallBold = bodySmallBold,
    bodyMediumSemiBold = bodyMediumSemiBold,
    bodyXSmallBold = bodyXSmallBold,
    bodyXSmallMedium = bodyXSmallMedium,
    bodyLarge = bodyLarge,
    bodyLargeMedium = bodyLargeMedium,
    bodyLargeBold = bodyLargeBold,
)
