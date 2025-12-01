package com.areazeroseven.dittohoosports.feature_scores.data.mappers

import com.areazeroseven.dittohoosports.feature_scores.data.remote.dto.EventStatusDto
import com.areazeroseven.dittohoosports.feature_scores.data.remote.dto.ScoreboardDataDto
import com.areazeroseven.dittohoosports.feature_scores.domain.GameStatus
import com.areazeroseven.dittohoosports.feature_scores.domain.NbaTeam
import com.areazeroseven.dittohoosports.feature_scores.domain.Matchup
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

fun ScoreboardDataDto.toNBAMatchup(): List<Matchup> {
    return events.flatMap { event ->
        event.competitions.map { competition ->
            val homeTeam = competition.competitors.find { it.homeAway == "home" }!!
            val awayTeam = competition.competitors.find { it.homeAway == "away" }!!

            Matchup(
                homeTeam = NbaTeam.fromValue(homeTeam.team.name),
                awayTeam = NbaTeam.fromValue(awayTeam.team.name),
                homeTeamScore = homeTeam.score.toInt(),
                awayTeamScore = awayTeam.score.toInt(),
                status = event.status.determineStatus(event.date)
            )
        }
    }
}

private fun EventStatusDto.determineStatus(date: String): GameStatus {
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