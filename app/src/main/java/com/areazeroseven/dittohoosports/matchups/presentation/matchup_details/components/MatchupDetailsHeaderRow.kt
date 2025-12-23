package com.areazeroseven.dittohoosports.matchups.presentation.matchup_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.areazeroseven.dittohoosports.matchups.presentation.matchup_details.MatchupDetailsState
import com.areazeroseven.dittohoosports.matchups.presentation.util.getMatchupStatusState

@Composable
fun MatchupDetailsHeaderRow(
    state: MatchupDetailsState,
    modifier: Modifier = Modifier
) {
    val matchupDetails = state.matchupDetails!! // Temporary non-null assertion
    val awayTeam = matchupDetails.awayTeam
    val homeTeam = matchupDetails.homeTeam
    val matchupStatusState = getMatchupStatusState(
        matchup = state.matchup!!, // Temporary non-null assertion
        isMatchupDetails = true
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(colorScheme.surface)
            .padding(start = 8.dp, end = 8.dp, bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        DetailsTeamRow(
            score = state.matchup.awayTeamScore,
            status = state.matchup.status,
            abbreviation = matchupDetails.awayTeamAbbreviation,
            isHomeTeam = false,
            isWinner = matchupStatusState.isAwayTeamWinner,
            logoResId = awayTeam.drawableResId,
            modifier = Modifier.width(125.dp)
        )
        Text(
            text = matchupStatusState.statusText,
            fontWeight = matchupStatusState.statusFontWeight,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
        DetailsTeamRow(
            score = state.matchup.homeTeamScore,
            status = state.matchup.status,
            abbreviation = matchupDetails.homeTeamAbbreviation,
            isHomeTeam = true,
            isWinner = matchupStatusState.isHomeTeamWinner,
            logoResId = homeTeam.drawableResId,
            modifier = Modifier.width(125.dp)
        )
    }
}