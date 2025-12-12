package com.areazeroseven.dittohoosports.matchups.presentation.matchup_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.areazeroseven.dittohoosports.R
import com.areazeroseven.dittohoosports.matchups.presentation.util.getMatchupStatusState

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

        state.matchupDetails?.let { details ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorScheme.surface)
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    details.awayTeam.drawableResId?.let {
                        DetailsTeamRow(it, details.awayTeamAbbreviation)
                    }
                    state.matchup?.let {
                        val matchupStatusState = getMatchupStatusState(it)
                        Text(text = matchupStatusState.statusText)
                    }
                    details.homeTeam.drawableResId?.let {
                        DetailsTeamRow(it, details.homeTeamAbbreviation)
                    }
                }
            }
        }
    }
}