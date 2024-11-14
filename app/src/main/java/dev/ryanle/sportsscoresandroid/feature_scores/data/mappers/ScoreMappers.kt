package dev.ryanle.sportsscoresandroid.feature_scores.data.mappers

import dev.ryanle.sportsscoresandroid.feature_scores.domain.Score
import dev.ryanle.sportsscoresandroid.feature_scores.data.remote.dto.EventsDataDto
import dev.ryanle.sportsscoresandroid.feature_scores.domain.NbaTeam

fun EventsDataDto.toNbaScore(): List<Score> {
    return data.map {
        Score(
            homeTeam = NbaTeam.fromValue(it.teams.home.names.nickname),
            awayTeam = NbaTeam.fromValue(it.teams.away.names.nickname),
            homeTeamScore = it.results?.regulation?.home?.points?.plus(
                it.results.overtime?.home?.points ?: 0
            ),
            awayTeamScore = it.results?.regulation?.away?.points?.plus(
                it.results.overtime?.away?.points ?: 0
            ),
            startsAt = it.status.startsAt,
            started = it.status.started,
            completed = it.status.completed
        )
    }
}