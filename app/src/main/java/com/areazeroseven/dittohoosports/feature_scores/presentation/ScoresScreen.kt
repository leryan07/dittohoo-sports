package com.areazeroseven.dittohoosports.feature_scores.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import com.areazeroseven.dittohoosports.feature_scores.domain.Matchup
import com.areazeroseven.dittohoosports.feature_scores.presentation.components.ErrorBanner
import com.areazeroseven.dittohoosports.feature_scores.presentation.components.ScoreItem
import com.areazeroseven.dittohoosports.feature_scores.presentation.components.ScoresDatePicker
import horizontalGradientBorder

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ScoresScreen(
    modifier: Modifier = Modifier,
    viewModel: ScoresViewModel = viewModel()
) {
    val state = viewModel.state
    val refreshState = rememberPullRefreshState(state.isRefreshing, viewModel::refresh)
    val commonSurfaceColor = colorScheme.surface

    Scaffold(
        modifier = modifier,
        topBar = {
            Column {
                Spacer(modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars))
                AnimatedVisibility(state.error != null) {
                    ErrorBanner(state.error?.code)
                }
                TopAppBar(
                    modifier = Modifier
                        .consumeWindowInsets(
                            WindowInsets.statusBars.only(
                                WindowInsetsSides.Top
                            )
                        ),
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .pullRefresh(refreshState),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item {
                    ScoresDatePicker(
                        state.scoresDate,
                        viewModel::fetchPreviousDay,
                        onNextDay = viewModel::fetchNextDay,
                        onDatePickerModalDateSelected = viewModel::onSelectedDate,
                        modifier = Modifier.background(commonSurfaceColor),
                    )
                }

                if (!state.isLoading) {
                    state.scoresList?.let { scores ->
                        item {
                            Spacer(modifier = Modifier.height(24.dp))
                            MatchupsList(state.scoresList, commonSurfaceColor)
                            Spacer(modifier = Modifier.height(24.dp))
                        }
                    }
                }
            }

            if (state.isLoading) {
                CircularProgressIndicator()
            }

            PullRefreshIndicator(
                state.isRefreshing,
                refreshState,
                Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

@Composable
private fun MatchupsList(
    matchups: List<Matchup>,
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