package com.areazeroseven.dittohoosports.matchups.domain

data class MatchupDetails(
    val awayTeam: Team,
    val homeTeam: Team,
    val awayTeamAbbreviation: String,
    val homeTeamAbbreviation: String,
    val awayTeamScore: String?,
    val homeTeamScore: String?,
    val awayTeamPlayerStats: List<PlayerStats>,
    val homeTeamPlayerStats: List<PlayerStats>
)