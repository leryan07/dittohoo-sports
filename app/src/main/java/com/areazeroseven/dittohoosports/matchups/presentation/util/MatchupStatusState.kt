package com.areazeroseven.dittohoosports.matchups.presentation.util

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.areazeroseven.dittohoosports.R
import com.areazeroseven.dittohoosports.matchups.domain.GameStatus
import com.areazeroseven.dittohoosports.matchups.domain.Matchup
import com.areazeroseven.dittohoosports.util.DateUtil
import com.areazeroseven.dittohoosports.util.DateUtil.DATE_TIME_FORMAT_EEE_MMdd
import com.areazeroseven.dittohoosports.util.DateUtil.DATE_TIME_FORMAT_hmm_a
import java.time.format.DateTimeFormatter

data class MatchupStatusState(
    val showScores: Boolean,
    val statusText: String,
    val statusFontColor: Color,
    val statusFontWeight: FontWeight,
    val isHomeTeamWinner: Boolean,
    val isAwayTeamWinner: Boolean
)

@Composable
fun getMatchupStatusState(matchup: Matchup): MatchupStatusState {
    var showScores = true
    var statusText: String
    var statusFontColor = MaterialTheme.colorScheme.onSurface
    var isHomeTeamWinner = matchup.homeTeamScore > matchup.awayTeamScore
    var isAwayTeamWinner = matchup.awayTeamScore > matchup.homeTeamScore

    when (matchup.status) {
        is GameStatus.Scheduled -> {
            showScores = false

            val startsAt = matchup.status.startsAt
            val daysFromCurrentDate =
                DateUtil.calculateDaysFromCurrentDay(startsAt)

            statusText = when (daysFromCurrentDate) {
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
            statusText = matchup.status.clock
            statusFontColor = if (isSystemInDarkTheme()) {
                colorResource(R.color.live_game_status_red_dark)
            } else {
                colorResource(R.color.live_game_status_red_light)
            }
            isHomeTeamWinner = false
            isAwayTeamWinner = false
        }

        is GameStatus.Completed -> {
            statusText = stringResource(R.string.final_status)
        }
    }

    return MatchupStatusState(
        showScores = showScores,
        statusText = statusText,
        statusFontColor = statusFontColor,
        statusFontWeight = FontWeight.Bold,
        isHomeTeamWinner = isHomeTeamWinner,
        isAwayTeamWinner = isAwayTeamWinner
    )
}