package com.areazeroseven.dittohoosports.matchups.presentation.matchup_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import clipRoundedCornerShape_25_dp
import com.areazeroseven.dittohoosports.R
import com.areazeroseven.dittohoosports.matchups.domain.Matchup

@Composable
fun StatsTeamTabRow(
    matchup: Matchup?,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val teams = listOf(
        matchup?.awayTeam,
        matchup?.homeTeam
    )

    SecondaryTabRow(
        containerColor = colorScheme.background,
        selectedTabIndex = selectedTabIndex,
        modifier = modifier
            .padding(16.dp)
            .clipRoundedCornerShape_25_dp(),
        indicator = {
            Box(
                Modifier
                    .tabIndicatorOffset(selectedTabIndex)
                    .fillMaxSize()
                    .padding(4.dp)
                    .clipRoundedCornerShape_25_dp()
                    .background(color = colorScheme.surface)
            )
        },
        divider = {}
    ) {
        CompositionLocalProvider(LocalRippleConfiguration provides null) {
            teams.forEachIndexed { index, team ->
                val defaultTeamNameResId = if (index == 0) R.string.away else R.string.home

                Tab(
                    selected = selectedTabIndex == index,
                    onClick = {
                        onTabSelected(index)
                    },
                    modifier = Modifier.zIndex(1f),
                    text = {
                        Text(
                            text = stringResource(team?.teamNameResId ?: defaultTeamNameResId),
                            fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                )
            }
        }
    }
}