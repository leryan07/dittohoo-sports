package com.areazeroseven.dittohoosports.core.data.remote.dto

import com.squareup.moshi.Json

data class SummaryEventDTO(
    @property:Json(name = "boxscore") val boxScore: BoxScoreDTO
)

data class BoxScoreDTO(
    val teams: List<BoxScoreTeamsDTO>,
    val players: List<PlayersDTO>?
)

data class BoxScoreTeamsDTO(
    val team: TeamDTO,
    val homeAway: String
)

data class PlayersDTO(
    val statistics: List<StatisticsDTO>,
    val displayOrder: Int
)

data class StatisticsDTO(
    val keys: List<String>,
    val athletes: List<AthletesDTO>,
    val totals: List<String>
)

data class AthletesDTO(
    val active: Boolean,
    val athlete: AthleteDTO,
    val stats: List<String>
)

data class AthleteDTO(
    val shortName: String
)