package dev.ryanle.sportsscoresandroid.feature_scores.data.remote.dto

data class TeamsDto(
    val away: TeamDto,
    val home: TeamDto
)

data class TeamDto(
    val names: NamesDto
)

data class NamesDto(
    val nickname: String
)
