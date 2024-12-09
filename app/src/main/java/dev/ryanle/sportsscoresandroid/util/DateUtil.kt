package dev.ryanle.sportsscoresandroid.util

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

fun LocalDateTime.formatDateTime(pattern: String): String {
    return try {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        this.format(formatter)
    } catch (e: RuntimeException) {
        "Invalid Pattern"
    }
}

object DateUtil {

    const val NEW_YORK_TIME_ZONE_ID = "America/New_York"
    const val DATE_TIME_FORMAT_PATTERN_1 = "EEE, MMM d"
    const val DATE_TIME_FORMAT_PATTERN_2 = "h:mm a"
    const val DATE_TIME_FORMAT_PATTERN_3 = "EEE, MM/dd"
    const val DATE_TIME_FORMAT_PATTERN_4 = "h:mm a"

    fun convertToLocalDateTime(date: String): LocalDateTime? {
        val utcInstant = Instant.parse(date)
        return utcInstant.atZone(ZoneId.systemDefault()).toLocalDateTime()
    }

    /**
     * Reference String should be in ISO 8601 format
     * @return Day and time or null
     */
    fun calculateDaysFromCurrentDay(isoDate: String): Int {
        val utcInstant = Instant.parse(isoDate)
        val referenceDateAsLocalDate = utcInstant.atZone(ZoneId.systemDefault()).toLocalDate()
        return ChronoUnit.DAYS.between(LocalDate.now(), referenceDateAsLocalDate).toInt()
    }
}