package dev.ryanle.sportsscoresandroid.feature_scores.data.remote.dto

data class EventStatusDto(
    val started: Boolean,
    val completed: Boolean,
    val finalized: Boolean,
    val startsAt: String,
    val live: Boolean,
    val clock: String?,
    val currentPeriodID: String?
)
