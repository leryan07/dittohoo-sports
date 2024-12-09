package dev.ryanle.sportsscoresandroid.feature_scores.domain

sealed class GameStatus {
    data class Scheduled(val startsAt: String) : GameStatus()
    data class Live(val clock: String, val currentPeriodID: String) : GameStatus()
    data object Completed : GameStatus()
}