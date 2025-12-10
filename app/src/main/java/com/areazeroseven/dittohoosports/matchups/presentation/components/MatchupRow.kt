package com.areazeroseven.dittohoosports.matchups.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.areazeroseven.dittohoosports.matchups.domain.Matchup
import com.areazeroseven.dittohoosports.matchups.presentation.util.getMatchupStatusState

@Composable
fun MatchupRow(
    matchup: Matchup,
    modifier: Modifier = Modifier
) {
    val matchupStatusState = getMatchupStatusState(matchup, false)
    val commonFontColor = MaterialTheme.colorScheme.onSurface

    Row(
        modifier = modifier
            .height(IntrinsicSize.Min)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            TeamRow(
                team = matchup.awayTeam,
                score = matchup.awayTeamScore,
                isWinner = matchupStatusState.isAwayTeamWinner,
                fontColor = commonFontColor,
                showScores = matchupStatusState.showScores,
            )
            TeamRow(
                team = matchup.homeTeam,
                score = matchup.homeTeamScore,
                isWinner = matchupStatusState.isHomeTeamWinner,
                fontColor = commonFontColor,
                showScores = matchupStatusState.showScores,
            )
        }
        VerticalDivider(color = commonFontColor)
        Column(
            modifier = Modifier
                .weight(0.5f)
                .padding(start = 16.dp)
        ) {
            Text(
                text = matchupStatusState.statusText,
                fontSize = 12.sp,
                fontWeight = matchupStatusState.statusFontWeight,
                color = matchupStatusState.statusFontColor,
                lineHeight = 16.sp
            )
        }
    }
}
