package dev.ryanle.sportsscoresandroid.feature_scores.data.remote.dto

import com.squareup.moshi.Json

data class EventResultsDto(
    @Json(name = "reg")
    val regulation: TeamPointsDto?,
    @Json(name = "ot")
    val overtime: TeamPointsDto?,
    val game: TeamPointsDto?
)

data class TeamPointsDto(
    val home: PointsDto,
    val away: PointsDto
)

data class PointsDto(
    val points: Int
)