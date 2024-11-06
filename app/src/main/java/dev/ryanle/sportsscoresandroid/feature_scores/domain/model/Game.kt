package dev.ryanle.sportsscoresandroid.feature_scores.domain.model

data class Game(
    val homeTeam: String,
    val awayTeam: String,
    val homeTeamScore: Int? = null,
    val awayTeamScore: Int? = null
)
