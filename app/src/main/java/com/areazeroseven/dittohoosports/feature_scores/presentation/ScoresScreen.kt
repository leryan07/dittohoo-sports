package com.areazeroseven.dittohoosports.feature_scores.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.areazeroseven.dittohoosports.R
import com.areazeroseven.dittohoosports.feature_scores.domain.Matchup
import com.areazeroseven.dittohoosports.util.DateUtil.DATE_TIME_FORMAT_EEE_MMM_d
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ScoresScreen(
    modifier: Modifier = Modifier,
    viewModel: ScoresViewModel = viewModel()
) {
    val state = viewModel.state
    val refreshState = rememberPullRefreshState(state.isRefreshing, viewModel::refresh)
    var showDatePicker by remember {
        mutableStateOf(false)
    }

    val commonBackgroundColor = if (isSystemInDarkTheme()) {
        colorResource(R.color.smoky_gray)
    } else {
        Color.White
    }

    if (showDatePicker) {
        DatePickerModal(
            onDateSelected = { date ->
                date?.let { viewModel.onSelectedDate(it) }
            },
            onDismiss = {
                showDatePicker = false
            },
            selectedDate = state.scoresDate.atZone(ZoneId.systemDefault()).toInstant()
                .toEpochMilli()
        )
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            Column {
                Spacer(modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars))
                AnimatedVisibility(state.error != null) {
                    val errorMessage = when (state.error?.code) {
                        429 -> {
                            stringResource(R.string.scores_error_too_many_requests)
                        }
                        else -> {
                            stringResource(R.string.scores_error)
                        }
                    }

                    Row(
                        modifier = Modifier
                            .background(colorResource(R.color.off_white))
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painterResource(R.drawable.baseline_warning_amber_24),
                            contentDescription = stringResource(R.string.warning),
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = errorMessage,
                            fontSize = 14.sp
                        )
                    }
                }
                TopAppBar(
                    modifier = Modifier.consumeWindowInsets(
                        WindowInsets.statusBars.only(
                            WindowInsetsSides.Top
                        )
                    ),
                    title = {
                        Text(text = stringResource(R.string.nba), fontWeight = FontWeight.Bold)
                    }
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
                    .consumeWindowInsets(innerPadding)
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(commonBackgroundColor)
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = viewModel::fetchPreviousDay
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.baseline_chevron_left_24),
                                contentDescription = stringResource(R.string.chevron_left_content_description),
                                modifier = Modifier.size(24.dp),
                                tint = MaterialTheme.colorScheme.secondaryContainer
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        TextButton(
                            onClick = {
                                showDatePicker = true
                            },
                            colors = ButtonColors(
                                containerColor = Color.Unspecified,
                                contentColor = MaterialTheme.colorScheme.primaryContainer,
                                disabledContainerColor = Color.Unspecified,
                                disabledContentColor = Color.Unspecified
                            )
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.outline_calendar_today_24),
                                contentDescription = stringResource(R.string.calendar_content_description),
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = state.scoresDate.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_EEE_MMM_d)),
                                fontSize = 18.sp
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(
                            onClick = viewModel::fetchNextDay
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.baseline_chevron_right_24),
                                contentDescription = stringResource(R.string.chevron_right_content_description),
                                modifier = Modifier.size(24.dp),
                                tint = MaterialTheme.colorScheme.secondaryContainer
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

                if (!state.isLoading) {
                    state.scoresList?.let { scores ->
                        scoresSuccessItem(scores, commonBackgroundColor)
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

private fun LazyListScope.scoresSuccessItem(
    matchups: List<Matchup>,
    backgroundColor: Color
) {
    itemsIndexed(matchups) { index, score ->
        val roundedCornerShape = if (matchups.size == 1) {
            RoundedCornerShape(30.dp)
        } else if (index == 0) {
            RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
        } else if (index == matchups.lastIndex) {
            RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)
        } else {
            RoundedCornerShape(0.dp)
        }

        Row(
            modifier = Modifier
                .clip(shape = roundedCornerShape)
                .background(backgroundColor)
        ) {
            Column {
                ScoreItem(
                    score,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )

                if (index < matchups.lastIndex) {
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.primaryContainer
                    )
                }
            }
        }
    }

    item {
        Spacer(modifier = Modifier.height(48.dp))
    }
}