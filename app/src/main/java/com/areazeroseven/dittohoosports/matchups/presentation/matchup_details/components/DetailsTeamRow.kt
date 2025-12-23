package com.areazeroseven.dittohoosports.matchups.presentation.matchup_details.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import borderRed
import com.areazeroseven.dittohoosports.R
import com.areazeroseven.dittohoosports.matchups.domain.GameStatus

@Composable
fun DetailsTeamRow(
    score: Int,
    status: GameStatus,
    abbreviation: String,
    isHomeTeam: Boolean,
    isWinner: Boolean,
    @DrawableRes logoResId: Int,
    modifier: Modifier = Modifier
) {
    var fontWeight = FontWeight.Bold

    when (status) {
        is GameStatus.Scheduled -> {
            TeamRow(modifier = modifier) {
                if (isHomeTeam) {
                    AbbreviationText(abbreviation)
                }
                TeamLogo(logoResId)
                if (!isHomeTeam) {
                    AbbreviationText(abbreviation)
                }
            }
        }

        is GameStatus.Completed -> {
            if (!isWinner) fontWeight = FontWeight.Normal

            TeamRow(modifier = modifier) {
                if (isHomeTeam) {
                    if (isWinner) {
                        Icon(
                            painter = painterResource(R.drawable.arrow_right_24),
                            contentDescription = null
                        )
                    } else {
                        Spacer(modifier = Modifier.width(24.dp))
                    }
                    ScoreText(score, fontWeight)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    TeamLogo(logoResId)
                    AbbreviationText(
                        abbreviation = abbreviation,
                        isScheduled = false
                    )
                }
                if (!isHomeTeam) {
                    ScoreText(score, fontWeight)
                    if (isWinner) {
                        Icon(
                            painter = painterResource(R.drawable.arrow_left_24),
                            contentDescription = null
                        )
                    } else {
                        Spacer(modifier = Modifier.width(24.dp))
                    }
                }
            }
        }

        is GameStatus.Live -> {

        }
    }
}

@Composable
private fun TeamRow(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        content = content
    )
}


@Composable
private fun TeamLogo(logoResId: Int) {
    Image(
        painterResource(logoResId),
        contentDescription = "",
        modifier = Modifier.size(48.dp)
    )
}

@Composable
private fun AbbreviationText(abbreviation: String, isScheduled: Boolean = true) {
    val fontSize = if (isScheduled) 28.sp else 14.sp
    Text(
        text = abbreviation,
        fontSize = fontSize,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun ScoreText(score: Int, fontWeight: FontWeight) {
    Text(
        text = score.toString(),
        fontSize = 28.sp,
        fontWeight = fontWeight
    )
}