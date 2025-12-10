package com.areazeroseven.dittohoosports.matchups.presentation.matchup_details.components

import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.areazeroseven.dittohoosports.matchups.presentation.matchup_details.MatchupDetailsIntent
import com.areazeroseven.dittohoosports.matchups.presentation.matchup_details.MatchupDetailsState
import com.areazeroseven.dittohoosports.matchups.presentation.matchup_details.MatchupDetailsTab

@Composable
fun MatchupDetailsTabRow(
    state: MatchupDetailsState,
    onIntent: (MatchupDetailsIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    SecondaryTabRow(
        selectedTabIndex = state.selectedDestination,
        modifier = modifier
    ) {
        MatchupDetailsTab.entries.forEachIndexed { index, destination ->
            Tab(
                selected = state.selectedDestination == index,
                onClick = {
                    onIntent(MatchupDetailsIntent.OnTabSelected(index))
                },
                text = {
                    Text(
                        text = destination.label.asString().uppercase(),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )
        }
    }
}