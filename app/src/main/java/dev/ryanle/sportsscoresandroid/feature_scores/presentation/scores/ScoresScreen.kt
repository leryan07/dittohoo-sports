package dev.ryanle.sportsscoresandroid.feature_scores.presentation.scores

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.ryanle.sportsscoresandroid.R
import dev.ryanle.sportsscoresandroid.feature_scores.domain.Score
import dev.ryanle.sportsscoresandroid.feature_scores.presentation.scores.components.DatePickerModal
import dev.ryanle.sportsscoresandroid.feature_scores.presentation.scores.components.ScoreItem
import dev.ryanle.sportsscoresandroid.util.DateUtil
import dev.ryanle.sportsscoresandroid.util.formatDateTime
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoresScreen(
    modifier: Modifier = Modifier,
    viewModel: ScoresViewModel = viewModel()
) {
    val state = viewModel.state
    var showDatePicker by remember {
        mutableStateOf(false)
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
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.nba), fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(R.color.off_white))
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = viewModel::fetchPreviousDay
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.baseline_chevron_left_24),
                                contentDescription = stringResource(R.string.chevron_left_content_description),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        TextButton(
                            onClick = {
                                showDatePicker = true
                            }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.outline_calendar_today_24),
                                contentDescription = stringResource(R.string.calendar_content_description),
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = state.scoresDate.formatDateTime(DateUtil.DATE_TIME_FORMAT_PATTERN_1),
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
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }

                if (!state.isLoading) {
                    state.scoresList?.let { scores ->
                        scoresSuccessItem(scores)
                    }
                }

            }

            if (state.isLoading) {
                CircularProgressIndicator()
            }
        }
    }
}

private fun LazyListScope.scoresSuccessItem(
    scores: List<Score>
) {
    itemsIndexed(scores) { index, score ->
        Column(modifier = Modifier.background(Color.White)) {
            ScoreItem(
                score,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            if (index < scores.lastIndex) {
                HorizontalDivider()
            }
        }
    }

    item {
        Spacer(modifier = Modifier.height(48.dp))
    }
}