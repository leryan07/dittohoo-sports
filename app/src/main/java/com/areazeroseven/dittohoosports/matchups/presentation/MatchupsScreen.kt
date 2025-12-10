package com.areazeroseven.dittohoosports.matchups.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.areazeroseven.dittohoosports.R
import com.areazeroseven.dittohoosports.matchups.domain.Matchup
import com.areazeroseven.dittohoosports.matchups.presentation.components.ErrorBanner
import com.areazeroseven.dittohoosports.matchups.presentation.components.ScoreItem
import com.areazeroseven.dittohoosports.matchups.presentation.components.ScoresDatePicker
import horizontalGradientBorder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoresScreen(
    onMatchupClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MatchupsViewModel = viewModel()
) {
    val state = viewModel.state
    val commonSurfaceColor = colorScheme.surface

    Scaffold(
        modifier = modifier,
        topBar = {
            Column {
                AnimatedVisibility(state.error != null) {
                    ErrorBanner(state.error?.code)
                }
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(R.string.nba),
                            fontWeight = FontWeight.Bold,
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = commonSurfaceColor,
                        titleContentColor = colorScheme.primaryContainer
                    )
                )
            }
        }
    ) { innerPadding ->
        PullToRefreshBox(
            isRefreshing = state.isRefreshing,
            onRefresh = viewModel::refresh,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item {
                    ScoresDatePicker(
                        state.date,
                        viewModel::fetchPreviousDay,
                        onNextDay = viewModel::fetchNextDay,
                        onDatePickerModalDateSelected = viewModel::onSelectedDate,
                        modifier = Modifier.background(commonSurfaceColor),
                    )
                }

                if (!state.isLoading) {
                    state.list?.let { scores ->
                        item {
                            Spacer(modifier = Modifier.height(24.dp))
                            MatchupsList(
                                state.list,
                                onMatchupClick,
                                commonSurfaceColor
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                        }
                    }
                }
            }

            if (state.isLoading) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
private fun MatchupsList(
    matchups: List<Matchup>,
    onNavigateToMatchupDetails: (String) -> Unit,
    backgroundColor: Color
) {
    Column(
        modifier = Modifier
            .clip(
                RoundedCornerShape(35.dp)
            )
            .background(backgroundColor)
    ) {
        matchups.forEachIndexed { index, score ->
            val topPadding = if (index == 0) 24.dp else 16.dp
            val bottomPadding = if (index == matchups.lastIndex) 24.dp else 16.dp
            val horizontalPadding = 16.dp

            Column {
                ScoreItem(
                    score,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = topPadding,
                            bottom = bottomPadding,
                            start = horizontalPadding,
                            end = horizontalPadding
                        )
                        .clickable(
                            enabled = true,
                            onClick = { onNavigateToMatchupDetails("testId") }
                        )
                )

                if (index < matchups.lastIndex) {
                    HorizontalDivider(
                        modifier = Modifier
                            .padding(horizontal = horizontalPadding)
                            .horizontalGradientBorder(),
                    )
                }
            }
        }
    }
}