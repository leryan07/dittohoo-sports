package dev.ryanle.sportsscoresandroid.feature_scores.presentation.scores.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import dev.ryanle.sportsscoresandroid.feature_scores.domain.GameStatus
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
            .height(IntrinsicSize.Min)
    ) {
        Column(modifier = Modifier.weight(0.7f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                var awayTeamFontWeight = FontWeight.Normal
                var awayTeamFontColor = Color.Unspecified

                if (winningTeam.lowercase() == "away" && score.status is GameStatus.Completed) {
                    awayTeamFontWeight = FontWeight.Bold
                    awayTeamFontColor =
                        if (isSystemInDarkTheme()) MaterialTheme.colorScheme.tertiaryContainer else Color.Unspecified
                }

                score.awayTeam.drawableResId?.let { resId ->
                    Image(
                        painter = painterResource(resId),
                        contentDescription = "",
                        modifier = Modifier.size(32.dp)
                    )
                } ?: LogoPlaceHolder()

                Text(
                    text = stringResource(score.awayTeam.teamNameResId),
                    modifier = Modifier
                        .padding(start = 4.dp),
                    fontWeight = awayTeamFontWeight,
                    color = awayTeamFontColor
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = score.awayTeamScore?.toString() ?: "",
                    fontWeight = awayTeamFontWeight,
                    color = awayTeamFontColor
                )

                if (winningTeam.lowercase() == "away" && score.status is GameStatus.Completed) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_arrow_left_24),
                        contentDescription = stringResource(R.string.arrow_left),
                        tint = awayTeamFontColor
                    )
                } else {
                    Spacer(modifier = Modifier.width(24.dp))
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                var homeTeamFontWeight = FontWeight.Normal
                var homeTeamFontColor = Color.Unspecified

                if (winningTeam.lowercase() == "home" && score.status is GameStatus.Completed) {
                    homeTeamFontWeight = FontWeight.Bold
                    homeTeamFontColor =
                        if (isSystemInDarkTheme()) MaterialTheme.colorScheme.tertiaryContainer else Color.Unspecified
                }

                score.homeTeam.drawableResId?.let { resId ->
                    Image(
                        painter = painterResource(resId),
                        contentDescription = "",
                        modifier = Modifier.size(32.dp)
                    )
                } ?: LogoPlaceHolder()

                Text(
                    text = stringResource(score.homeTeam.teamNameResId),
                    modifier = Modifier.padding(start = 4.dp),
                    fontWeight = homeTeamFontWeight,
                    color = homeTeamFontColor
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = score.homeTeamScore?.toString() ?: "",
                    fontWeight = homeTeamFontWeight,
                    color = homeTeamFontColor
                )

                if (winningTeam.lowercase() == "home" && score.status is GameStatus.Completed) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_arrow_left_24),
                        contentDescription = stringResource(R.string.arrow_left),
                        tint = homeTeamFontColor
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
            val status = when (score.status) {
                is GameStatus.Scheduled -> {
                    val startsAt = convertToLocalDateTime(score.status.startsAt)
                    val daysFromCurrentDate = calculateDaysFromCurrentDay(score.status.startsAt)

                    when (daysFromCurrentDate) {
                        0 -> stringResource(
                            R.string.today_game_date_time,
                            startsAt?.formatDateTime(DATE_TIME_FORMAT_PATTERN_2) ?: "N/A"
                        )

                        1 -> stringResource(
                            R.string.tomorrow_game_date_time,
                            startsAt?.formatDateTime(DATE_TIME_FORMAT_PATTERN_2) ?: "N/A"
                        )

                        else -> {
                            val dateDay = startsAt?.formatDateTime(DATE_TIME_FORMAT_PATTERN_3)
                            val dateTime = startsAt?.formatDateTime(DATE_TIME_FORMAT_PATTERN_4)
                            "$dateDay\n$dateTime"
                        }
                    }
                }

                is GameStatus.Live -> {
                    val quarter = when (score.status.currentPeriodID) {
                        "1q" -> stringResource(R.string.q1)
                        "2q" -> stringResource(R.string.q2)
                        "3q" -> stringResource(R.string.q3)
                        "4q" -> stringResource(R.string.q4)
                        else -> ""
                    }

                    "$quarter, ${score.status.clock}"
                }

                is GameStatus.Completed -> {
                    stringResource(R.string.final_status)
                }
            }

            var statusFontWeight = FontWeight.Normal
            var statusFontColor = Color.Unspecified

            if (score.status is GameStatus.Completed) {
                statusFontWeight = FontWeight.Bold
                statusFontColor =
                    if (isSystemInDarkTheme()) MaterialTheme.colorScheme.tertiaryContainer else Color.Unspecified
            } else if (score.status is GameStatus.Live) {
                statusFontColor = Color.Red
            }

            Box(
                modifier = Modifier.height(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = status,
                    fontSize = 12.sp,
                    fontWeight = statusFontWeight,
                    lineHeight = 16.sp,
                    color = statusFontColor
                )
            }
        }
    }
}

@Composable
private fun LogoPlaceHolder(modifier: Modifier = Modifier) {
    Box(modifier.size(32.dp))
}