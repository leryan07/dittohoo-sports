package com.areazeroseven.dittohoosports.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

object DateUtil {

    const val NEW_YORK_TIME_ZONE_ID = "America/New_York"
    const val DATE_TIME_FORMAT_EEE_MMM_d = "EEE, MMM d"
    const val DATE_TIME_FORMAT_EEE_MMdd = "EEE, MM/dd"
    const val DATE_TIME_FORMAT_hmm_a = "h:mm a"
    const val DATE_TIME_FORMAT_yyyyMMdd = "yyyyMMdd"

    fun calculateDaysFromCurrentDay(localDateTime: LocalDateTime?): Int {
        return ChronoUnit.DAYS.between(
            LocalDate.now(),
            localDateTime?.toLocalDate()
        ).toInt()
    }
}