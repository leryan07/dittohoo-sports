package com.areazeroseven.dittohoosports.matchups.domain

data class Matchup(
    val homeTeam: Team,
    val awayTeam: Team,
    val homeTeamScore: Int,
    val awayTeamScore: Int,
    val status: GameStatus
)
