package dev.ryanle.sportsscoresandroid.feature_scores.presentation.scores.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ryanle.sportsscoresandroid.R
import dev.ryanle.sportsscoresandroid.feature_scores.domain.Score
import dev.ryanle.sportsscoresandroid.util.DateUtil.DATE_TIME_FORMAT_PATTERN_2
import dev.ryanle.sportsscoresandroid.util.DateUtil.DATE_TIME_FORMAT_PATTERN_3
import dev.ryanle.sportsscoresandroid.util.DateUtil.DATE_TIME_FORMAT_PATTERN_4
import dev.ryanle.sportsscoresandroid.util.DateUtil.calculateDaysFromCurrentDay
import dev.ryanle.sportsscoresandroid.util.DateUtil.convertToLocalDateTime
import dev.ryanle.sportsscoresandroid.util.formatDateTime

@Composable
fun ScoreItem(
    score: Score,
    modifier: Modifier = Modifier
) {
    var winningTeam = ""

    score.homeTeamScore?.let { homeScore ->
        score.awayTeamScore?.let { awayScore ->
            winningTeam = if (homeScore > awayScore) {
                "home"
            } else {
                "away"
            }
        }
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .height(IntrinsicSize.Min)
    ) {
        Column(modifier = Modifier.weight(0.7f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                val fontWeight = if (winningTeam.lowercase() == "away") {
                    FontWeight.Bold
                } else {
                    FontWeight.Normal
                }

                score.awayTeam.drawableResId?.let { resId ->
                    Image(
                        painter = painterResource(resId),
                        contentDescription = "",
                        modifier = Modifier.size(32.dp)
                    )
                } ?: LogoPlaceHolder()

                Text(
                    text = score.awayTeam.teamName,
                    modifier = Modifier
                        .padding(start = 4.dp),
                    fontWeight = fontWeight
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(text = score.awayTeamScore?.toString() ?: "", fontWeight = fontWeight)

                if (winningTeam.lowercase() == "away") {
                    Icon(
                        painter = painterResource(R.drawable.baseline_arrow_left_24),
                        contentDescription = "Arrow Left",
                    )
                } else {
                    Spacer(modifier = Modifier.width(24.dp))
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                val fontWeight = if (winningTeam.lowercase() == "home") {
                    FontWeight.Bold
                } else {
                    FontWeight.Normal
                }

                score.homeTeam.drawableResId?.let { resId ->
                    Image(
                        painter = painterResource(resId),
                        contentDescription = "",
                        modifier = Modifier.size(32.dp)
                    )
                } ?: LogoPlaceHolder()

                Text(
                    text = score.homeTeam.teamName,
                    modifier = Modifier.padding(start = 4.dp),
                    fontWeight = fontWeight
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(text = score.homeTeamScore?.toString() ?: "", fontWeight = fontWeight)

                if (winningTeam.lowercase() == "home") {
                    Icon(
                        painter = painterResource(R.drawable.baseline_arrow_left_24),
                        contentDescription = "Arrow Left"
                    )
                } else {
                    Spacer(modifier = Modifier.width(24.dp))
                }
            }
        }
        VerticalDivider()
        Column(
            modifier = Modifier
                .weight(0.3f)
                .padding(horizontal = 16.dp)
        ) {
            val daysFromCurrentDate = calculateDaysFromCurrentDay(score.startsAt)

            val status = if (score.started && !score.completed) {
                "N/A"
            } else if (score.completed) {
                stringResource(R.string.final_status)
            } else {
                val startsAt = convertToLocalDateTime(score.startsAt)

                when (daysFromCurrentDate) {
                    0 -> "Today ${startsAt?.formatDateTime(DATE_TIME_FORMAT_PATTERN_2)}"
                    1 -> "Tomorrow\n${startsAt?.formatDateTime(DATE_TIME_FORMAT_PATTERN_2)}"
                    else -> {
                        val dateDay = startsAt?.formatDateTime(DATE_TIME_FORMAT_PATTERN_3)
                        val dateTime = startsAt?.formatDateTime(DATE_TIME_FORMAT_PATTERN_4)
                        "$dateDay\n$dateTime"
                    }
                }
            }

            val fontWeight = if (score.completed) {
                FontWeight.Bold
            } else {
                FontWeight.Normal
            }

            Box(
                modifier = Modifier.height(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = status,
                    fontSize = 12.sp,
                    fontWeight = fontWeight,
                    lineHeight = 16.sp
                )
            }
        }
    }
}

@Composable
private fun LogoPlaceHolder(modifier: Modifier = Modifier) {
    Box(modifier.size(32.dp))
}