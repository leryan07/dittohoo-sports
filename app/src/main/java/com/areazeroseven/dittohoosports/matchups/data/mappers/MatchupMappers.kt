package com.areazeroseven.dittohoosports.matchups.data.mappers

import com.areazeroseven.dittohoosports.core.data.remote.dto.EventStatusDTO
import com.areazeroseven.dittohoosports.core.data.remote.dto.ScoreboardDataDTO
import com.areazeroseven.dittohoosports.matchups.domain.GameStatus
import com.areazeroseven.dittohoosports.matchups.domain.Matchup
import com.areazeroseven.dittohoosports.matchups.domain.NbaTeam
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

fun ScoreboardDataDTO.toNBAMatchup(): List<Matchup> {
    return events.flatMap { event ->
        event.competitions.map { competition ->
            val homeTeam = competition.competitors.find { it.homeAway == "home" }!!
            val awayTeam = competition.competitors.find { it.homeAway == "away" }!!

            Matchup(
                id = event.id,
                homeTeam = NbaTeam.fromValue(homeTeam.team.name),
                awayTeam = NbaTeam.fromValue(awayTeam.team.name),
                homeTeamScore = homeTeam.score.toInt(),
                awayTeamScore = awayTeam.score.toInt(),
                status = event.status.determineStatus(event.date)
            )
        }
    }
}

private fun EventStatusDTO.determineStatus(date: String): GameStatus {
    return when (this.type.state) {
        "post" -> GameStatus.Completed
        "in" -> GameStatus.Live(this.type.shortDetail)
        else -> {
            val dateWithSeconds = date.replace("Z", ":00Z")
            val instant = Instant.parse(dateWithSeconds)
            val localDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.systemDefault())
            GameStatus.Scheduled(localDateTime)
        }
    }
}