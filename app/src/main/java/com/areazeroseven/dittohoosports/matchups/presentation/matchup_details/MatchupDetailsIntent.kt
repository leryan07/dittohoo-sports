package com.areazeroseven.dittohoosports.matchups.presentation.matchup_details

sealed interface MatchupDetailsIntent {
    data class LoadMatchupDetails(val id: String, val date: String) : MatchupDetailsIntent
}