package com.areazeroseven.dittohoosports.feature_scores.data.remote.dto

data class ScoreboardDataDto(
    val events: List<EventDto>
)

data class EventDto(
    val id: String,
    val date: String,
    val name: String,
    val competitions: List<CompetitionDto>,
    val status: EventStatusDto
)

data class CompetitionDto(
    val competitors: List<Competitor>,
)

data class Competitor(
    val homeAway: String,
    val winner: Boolean?,
    val team: TeamDto,
    val score: String
)