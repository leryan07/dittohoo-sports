package com.areazeroseven.dittohoosports.feature_scores.presentation.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
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
    var showScores = true
    val statusFontWeight = FontWeight.Bold
    val commonFontColor = MaterialTheme.colorScheme.onSurface
    var statusFontColor = commonFontColor

    var status: String

    var isHomeTeamWinner = matchup.homeTeamScore > matchup.awayTeamScore
    var isAwayTeamWinner = matchup.awayTeamScore > matchup.homeTeamScore

    when (matchup.status) {
        is GameStatus.Scheduled -> {
            showScores = false

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
            status = matchup.status.clock
            statusFontColor = if (isSystemInDarkTheme()) {
                colorResource(R.color.live_game_status_red_dark)
            } else {
                colorResource(R.color.live_game_status_red_light)
            }
            isHomeTeamWinner = false
            isAwayTeamWinner = false
        }

        is GameStatus.Completed -> {
            status = stringResource(R.string.final_status)
        }
    }

    Row(
        modifier = modifier
            .height(IntrinsicSize.Min)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            TeamRow(
                team = matchup.awayTeam,
                score = matchup.awayTeamScore,
                isWinner = isAwayTeamWinner,
                fontColor = commonFontColor,
                showScores = showScores,
            )
            TeamRow(
                team = matchup.homeTeam,
                score = matchup.homeTeamScore,
                isWinner = isHomeTeamWinner,
                fontColor = commonFontColor,
                showScores = showScores,
            )
        }
        VerticalDivider(color = commonFontColor)
        Column(
            modifier = Modifier
                .weight(0.5f)
                .padding(start = 16.dp)
        ) {
            Text(
                text = status,
                fontSize = 12.sp,
                fontWeight = statusFontWeight,
                color = statusFontColor,
                lineHeight = 16.sp
            )
        }
    }
}
