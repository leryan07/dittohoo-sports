package com.areazeroseven.dittohoosports.feature_scores.domain

data class Matchup(
    val homeTeam: Team,
    val awayTeam: Team,
    val homeTeamScore: Int,
    val awayTeamScore: Int,
    val status: GameStatus
)
