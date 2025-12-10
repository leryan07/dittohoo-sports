package com.areazeroseven.dittohoosports.matchups.presentation.matchup_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.areazeroseven.dittohoosports.matchups.domain.PlayerStatColumn
import com.areazeroseven.dittohoosports.matchups.domain.PlayerStatColumn.DEFENSIVE_REBOUNDS
import com.areazeroseven.dittohoosports.matchups.domain.PlayerStatColumn.OFFENSIVE_REBOUNDS
import com.areazeroseven.dittohoosports.matchups.presentation.matchup_details.MatchupDetailsIntent
import com.areazeroseven.dittohoosports.matchups.presentation.matchup_details.MatchupDetailsState

@Composable
fun PlayerStatsSection(
    state: MatchupDetailsState,
    onIntent: (MatchupDetailsIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedTeamStatsIndex = state.selectedTeamStatsIndex
    val teamPlayerStats = when (selectedTeamStatsIndex) {
        0 -> state.matchupDetails?.awayTeamPlayerStats ?: emptyList()
        else -> state.matchupDetails?.homeTeamPlayerStats ?: emptyList()
    }
    val statColumns = PlayerStatColumn.entries.filter {
        it != OFFENSIVE_REBOUNDS && it != DEFENSIVE_REBOUNDS
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colorScheme.surface)
    ) {
        StatsTeamTabRow(
            matchup = state.matchup,
            selectedTabIndex = selectedTeamStatsIndex,
            onTabSelected = { index ->
                onIntent(MatchupDetailsIntent.OnTeamStatsSelected(index))
            }
        )
        PlayerStatsTable(
            teamPlayerStats = teamPlayerStats,
            statColumns = statColumns
        )
    }
}