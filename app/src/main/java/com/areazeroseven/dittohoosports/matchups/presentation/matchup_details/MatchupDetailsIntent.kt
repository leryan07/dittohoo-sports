package com.areazeroseven.dittohoosports.matchups.presentation.matchup_details

sealed interface MatchupDetailsIntent {
    data class LoadMatchupDetails(val id: String, val date: String) : MatchupDetailsIntent
    data class OnTabSelected(val index: Int) : MatchupDetailsIntent
    data class OnTeamStatsSelected(val index: Int) : MatchupDetailsIntent
}