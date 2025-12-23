package com.areazeroseven.dittohoosports.matchups.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.areazeroseven.dittohoosports.R
import com.areazeroseven.dittohoosports.matchups.domain.Team

@Composable
fun TeamRow(
    team: Team,
    score: Int,
    isWinner: Boolean,
    fontColor: Color,
    showScores: Boolean
) {
    val commonFontWeight = if (isWinner) FontWeight.Bold else FontWeight.Normal

    Row(verticalAlignment = Alignment.CenterVertically) {
        team.drawableResId.let { resId ->
            Image(
                painter = painterResource(resId),
                contentDescription = "",
                modifier = Modifier.size(32.dp)
            )
        }
        Text(
            text = stringResource(team.teamNameResId),
            modifier = Modifier
                .padding(start = 4.dp),
            fontWeight = commonFontWeight,
            color = fontColor
        )
        Spacer(modifier = Modifier.weight(1f))
        if (showScores) {
            Text(
                text = score.toString(),
                fontWeight = commonFontWeight,
                color = fontColor
            )
        }
        if (isWinner) {
            Icon(
                painter = painterResource(R.drawable.arrow_left_24),
                contentDescription = stringResource(R.string.arrow_left),
                tint = fontColor
            )
        } else {
            Spacer(modifier = Modifier.width(24.dp))
        }
    }
}
