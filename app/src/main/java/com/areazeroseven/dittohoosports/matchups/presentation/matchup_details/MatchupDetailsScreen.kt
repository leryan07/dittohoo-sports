package com.areazeroseven.dittohoosports.matchups.presentation.matchup_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.areazeroseven.dittohoosports.R
import com.areazeroseven.dittohoosports.core.presentation.components.DevelopmentInProgress
import com.areazeroseven.dittohoosports.matchups.presentation.matchup_details.components.MatchupDetailsHeaderRow
import com.areazeroseven.dittohoosports.matchups.presentation.matchup_details.components.MatchupDetailsTabRow
import com.areazeroseven.dittohoosports.matchups.presentation.matchup_details.components.PlayerStatsSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchupDetailsScreen(
    id: String,
    date: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MatchupDetailsViewModel = viewModel()
) {
    val state = viewModel.state

    LaunchedEffect(Unit) {
        viewModel.onIntent(MatchupDetailsIntent.LoadMatchupDetails(id, date))
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = onBack
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.arrow_back_24),
                            contentDescription = stringResource(R.string.arrow_back)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        if (state.isLoading) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }

        state.matchupDetails?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                MatchupDetailsHeaderRow(
                    state = state
                )
                MatchupDetailsTabRow(
                    state = state,
                    onIntent = viewModel::onIntent
                )
                when (state.selectedDestination) {
                    MatchupDetailsTab.STATS.ordinal -> {
                        PlayerStatsSection(
                            state = state,
                            onIntent = viewModel::onIntent
                        )
                    }

                    else -> {
                        DevelopmentInProgress()
                    }
                }
            }
        }
    }
}