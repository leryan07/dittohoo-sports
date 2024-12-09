package dev.ryanle.sportsscoresandroid.feature_scores.domain

data class Score(
    val homeTeam: Team,
    val awayTeam: Team,
    val homeTeamScore: Int? = null,
    val awayTeamScore: Int? = null,
    val status: GameStatus
)
