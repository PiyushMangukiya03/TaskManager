package interactive.taskmanager.utils

import java.text.SimpleDateFormat
import java.time.Duration as JavaDuration
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import kotlin.time.Duration
import kotlin.time.Duration as KotlinDuration
import kotlin.time.toKotlinDuration

const val PatternMonthDayCommaYear = "MMMM d, yyyy"

fun Date.toLocalDateTime(): LocalDateTime =
    LocalDateTime.ofInstant(this.toInstant(), ZoneId.systemDefault())

fun LocalDateTime.toFormattedString(pattern: String): String =
    this.format(DateTimeFormatter.ofPattern(pattern))

/**
 *  Returns string represents standardized UTC time format "2023-06-15T00:00:00Z" used in Firebase
 * */
fun LocalDateTime.toStringUTC(): String {
    return this.atZone(ZoneId.systemDefault())
        .withZoneSameInstant(ZoneOffset.UTC)
        .format(DateTimeFormatter.ISO_DATE_TIME)
}

/**
 *  Returns string represents local time with timezone offset - format "2023-08-29T18:32:23.50665+02:00"
 * */
fun LocalDateTime.toStringWithTimezoneOffset(): String {
    return this.atZone(ZoneId.systemDefault())
        .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
}

/**
 *  Returns LocalDateTime parsed from standardized UTC time format "2023-06-15T00:00:00Z" used in Firebase
 * */
fun String.toLocalDateTime(): LocalDateTime {
    return this.let {
        OffsetDateTime.parse(it, DateTimeFormatter.ISO_DATE_TIME)
            .atZoneSameInstant(ZoneId.systemDefault())
            .toLocalDateTime()
    }
}

fun String.toDate(format: String): Date? = SimpleDateFormat(format, Locale.getDefault()).parse(this)

/**
 *  Returns duration between 2 date time objects.
 *  Order matters - could return negative duration
 * */
fun durationBetween(sooner: LocalDateTime, later: LocalDateTime): KotlinDuration {
    return JavaDuration.between(sooner, later).toKotlinDuration()
}

/**
 *  Returns number of calendar days between 2 date time objects.
 *  Order matters - could return negative duration
 * */
fun calendarDaysBetween(sooner: LocalDateTime, later: LocalDateTime): Int {
    return ChronoUnit.DAYS.between(sooner.toLocalDate(), later.toLocalDate()).toInt()
}

fun isSameDay(a: LocalDateTime, b: LocalDateTime): Boolean {
    return a.year == b.year && a.month == b.month && a.dayOfMonth == b.dayOfMonth
}

fun isSameYear(a: LocalDateTime, b: LocalDateTime): Boolean {
    return a.year == b.year
}

fun LocalDate.toDate() = Date.from(this.atStartOfDay(ZoneId.systemDefault()).toInstant())

fun LocalDate.toUtcMillis() = this.atStartOfDay(ZoneId.of("UTC")).toInstant().toEpochMilli()

fun LocalDate?.isToday() = this == LocalDate.now()

fun LocalTime.isBeforeNow() = this.isBefore(LocalTime.now())

fun Duration.toFormattedTime() = this.toComponents { minutes, seconds, _ ->
    "$minutes:${if (seconds < 10) "0" else ""}$seconds"
}

fun LocalDateTime.toMessageSent() = when {
    isSameDay(this, LocalDateTime.now()) -> this.toFormattedString("hh:mm a")
    isSameYear(this, LocalDateTime.now()) -> this.toFormattedString("dd MMM")
    else -> this.toFormattedString("MMM yyyy")
}

fun LocalDateTime?.toEpochMillis() = this?.atZone(ZoneId.systemDefault())
    ?.toInstant()?.toEpochMilli() ?: 0L

fun LocalDateTime.toUTCString() = atOffset(ZoneOffset.UTC).toString()

fun Long.toLocalDateTime(): LocalDateTime = LocalDateTime.ofInstant(
    Instant.ofEpochMilli(this),
    TimeZone.getDefault().toZoneId(),
)
