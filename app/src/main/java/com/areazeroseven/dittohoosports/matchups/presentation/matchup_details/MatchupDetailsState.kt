package com.areazeroseven.dittohoosports.matchups.presentation.matchup_details

import com.areazeroseven.dittohoosports.matchups.domain.Matchup
import com.areazeroseven.dittohoosports.matchups.domain.MatchupDetails

data class MatchupDetailsState(
    val matchup: Matchup? = null,
    val matchupDetails: MatchupDetails? = null,
    val selectedDestination: Int = MatchupDetailsTab.STATS.ordinal,
    val selectedTeamStatsIndex: Int = 0,
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false
)