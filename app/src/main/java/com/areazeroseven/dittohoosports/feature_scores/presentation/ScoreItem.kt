package com.areazeroseven.dittohoosports.feature_scores.presentation

import androidx.compose.foundation.Image
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
import com.areazeroseven.dittohoosports.R
import com.areazeroseven.dittohoosports.feature_scores.domain.GameStatus
import com.areazeroseven.dittohoosports.feature_scores.domain.Matchup
import com.areazeroseven.dittohoosports.util.DateUtil
import com.areazeroseven.dittohoosports.util.DateUtil.DATE_TIME_FORMAT_EEE_MMdd
import com.areazeroseven.dittohoosports.util.DateUtil.DATE_TIME_FORMAT_hmm_a
import java.time.format.DateTimeFormatter

@Composable
fun ScoreItem(
    matchup: Matchup,
    modifier: Modifier = Modifier
) {
    var showScores: Boolean
    var status: String
    var statusFontWeight: FontWeight
    var statusFontColor: Color
    val teamFontColor = MaterialTheme.colorScheme.onSurface

    val winningTeam = if (matchup.homeTeamScore > matchup.awayTeamScore) {
        "home"
    } else if (matchup.homeTeamScore < matchup.awayTeamScore) {
        "away"
    } else {
        ""
    }

    when (matchup.status) {
        is GameStatus.Scheduled -> {
            showScores = false
            statusFontWeight = FontWeight.Bold
            statusFontColor = Color.Unspecified

            val startsAt = matchup.status.startsAt
            val daysFromCurrentDate =
                DateUtil.calculateDaysFromCurrentDay(startsAt)

            status = when (daysFromCurrentDate) {
                0 -> startsAt?.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_hmm_a)) ?: "N/A"

                1 -> stringResource(
                    R.string.tomorrow_game_date_time,
                    startsAt?.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_hmm_a)) ?: "N/A"
                )

                else -> {
                    val dateDay = startsAt?.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_EEE_MMdd))
                    val dateTime = startsAt?.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_hmm_a))
                    "$dateDay\n$dateTime"
                }
            }
        }

        is GameStatus.Live -> {
            showScores = true
            status = matchup.status.clock
            statusFontWeight = FontWeight.Normal
            statusFontColor = Color.Red
        }

        is GameStatus.Completed -> {
            showScores = true
            status = stringResource(R.string.final_status)
            statusFontWeight = FontWeight.Bold
            statusFontColor = MaterialTheme.colorScheme.onSurface
        }
    }

    Row(
        modifier = modifier
            .height(IntrinsicSize.Min)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                var awayTeamFontWeight = FontWeight.Normal

                if (winningTeam.lowercase() == "away") {
                    awayTeamFontWeight = FontWeight.Bold
                }

                matchup.awayTeam.drawableResId?.let { resId ->
                    Image(
                        painter = painterResource(resId),
                        contentDescription = "",
                        modifier = Modifier.size(32.dp)
                    )
                } ?: LogoPlaceHolder()

                Text(
                    text = stringResource(matchup.awayTeam.teamNameResId),
                    modifier = Modifier
                        .padding(start = 4.dp),
                    fontWeight = awayTeamFontWeight,
                    color = teamFontColor
                )
                Spacer(modifier = Modifier.weight(1f))
                if (showScores) {
                    Text(
                        text = matchup.awayTeamScore.toString(),
                        fontWeight = awayTeamFontWeight,
                        color = teamFontColor
                    )
                }
                if (winningTeam.lowercase() == "away") {
                    Icon(
                        painter = painterResource(R.drawable.baseline_arrow_left_24),
                        contentDescription = stringResource(R.string.arrow_left),
                        tint = MaterialTheme.colorScheme.secondaryContainer
                    )
                } else {
                    Spacer(modifier = Modifier.width(24.dp))
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                var homeTeamFontWeight = FontWeight.Normal

                if (winningTeam.lowercase() == "home") {
                    homeTeamFontWeight = FontWeight.Bold
                }

                matchup.homeTeam.drawableResId?.let { resId ->
                    Image(
                        painter = painterResource(resId),
                        contentDescription = "",
                        modifier = Modifier.size(32.dp)
                    )
                } ?: LogoPlaceHolder()
                Text(
                    text = stringResource(matchup.homeTeam.teamNameResId),
                    modifier = Modifier.padding(start = 4.dp),
                    fontWeight = homeTeamFontWeight,
                    color = teamFontColor
                )
                Spacer(modifier = Modifier.weight(1f))
                if (showScores) {
                    Text(
                        text = matchup.homeTeamScore.toString(),
                        fontWeight = homeTeamFontWeight,
                        color = teamFontColor
                    )
                }
                if (winningTeam.lowercase() == "home") {
                    Icon(
                        painter = painterResource(R.drawable.baseline_arrow_left_24),
                        contentDescription = stringResource(R.string.arrow_left),
                        tint = MaterialTheme.colorScheme.secondaryContainer
                    )
                } else {
                    Spacer(modifier = Modifier.width(24.dp))
                }
            }
        }
        VerticalDivider(color = MaterialTheme.colorScheme.secondaryContainer)
        Column(
            modifier = Modifier
                .weight(0.5f)
                .padding(start = 16.dp)
        ) {
            Text(
                text = status,
                fontSize = 10.sp,
                fontWeight = statusFontWeight,
                color = statusFontColor,
                lineHeight = 16.sp
            )
        }
    }
}

@Composable
private fun LogoPlaceHolder(modifier: Modifier = Modifier) {
    Box(modifier.size(32.dp))
}