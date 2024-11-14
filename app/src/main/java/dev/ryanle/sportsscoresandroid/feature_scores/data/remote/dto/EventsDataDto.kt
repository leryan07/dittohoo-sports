package dev.ryanle.sportsscoresandroid.feature_scores.data.remote.dto

data class EventsDataDto(
    val data: List<EventInfo>
)

data class EventInfo(
    val results: EventResultsDto?,
    val teams: TeamsDto,
    val status: EventStatusDto
)