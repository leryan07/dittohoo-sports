package com.areazeroseven.dittohoosports.core.data.remote.dto

data class ScoreboardDataDTO(
    val events: List<EventDTO>
)

data class EventDTO(
    val id: String,
    val date: String,
    val name: String,
    val competitions: List<CompetitionDTO>,
    val status: EventStatusDTO
)

data class CompetitionDTO(
    val competitors: List<CompetitorDTO>,
)

data class CompetitorDTO(
    val homeAway: String,
    val winner: Boolean?,
    val team: TeamDTO,
    val score: String
)

data class EventStatusDTO(
    val displayClock: String,
    val period: Int,
    val type: StatusTypeDTO
)

data class StatusTypeDTO(
    val name: String,
    val state: String,
    val completed: Boolean,
    val description: String,
    val shortDetail: String
)
