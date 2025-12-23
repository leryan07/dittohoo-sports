package com.areazeroseven.dittohoosports.matchups.data.mappers

import com.areazeroseven.dittohoosports.core.data.remote.dto.SummaryEventDTO
import com.areazeroseven.dittohoosports.matchups.domain.MatchupDetails
import com.areazeroseven.dittohoosports.matchups.domain.NbaTeam

fun SummaryEventDTO.toNBAMatchupDetails(): MatchupDetails {
    val awayTeam = boxScore.teams.find { it.homeAway.lowercase() == "away" }?.team
    val homeTeam = boxScore.teams.find { it.homeAway.lowercase() == "home" }?.team

    val awayTeamName = awayTeam?.name ?: ""
    val homeTeamName = homeTeam?.name ?: ""
    val awayTeamPlayers = boxScore.players?.find { it.displayOrder == 1 }
    val homeTeamPlayers = boxScore.players?.find { it.displayOrder == 2 }

    val awayTeamScore = awayTeamPlayers?.statistics?.first()?.totals[1]
    val homeTeamScore = homeTeamPlayers?.statistics?.first()?.totals[1]

    return MatchupDetails(
        awayTeam = NbaTeam.fromValue(awayTeamName),
        homeTeam = NbaTeam.fromValue(homeTeamName),
        awayTeamAbbreviation = awayTeam?.abbreviation ?: "",
        homeTeamAbbreviation = homeTeam?.abbreviation ?: "",
        awayTeamScore = awayTeamScore,
        homeTeamScore = homeTeamScore,
        awayTeamPlayerStats = awayTeamPlayers?.toPlayerStats() ?: emptyList(),
        homeTeamPlayerStats = homeTeamPlayers?.toPlayerStats() ?: emptyList()
    )
}