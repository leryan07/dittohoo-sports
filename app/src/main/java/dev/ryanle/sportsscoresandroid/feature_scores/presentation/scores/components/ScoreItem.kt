package dev.ryanle.sportsscoresandroid.feature_scores.presentation.scores.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import dev.ryanle.sportsscoresandroid.R
import dev.ryanle.sportsscoresandroid.feature_scores.domain.model.Game

@Composable
fun ScoreItem(
    game: Game,
    modifier: Modifier = Modifier
) {
    var winningTeam = ""

    game.homeTeamScore?.let { homeScore ->
        game.awayTeamScore?.let { awayScore ->
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

                Image(
                    painter = painterResource(R.drawable.indiana_pacers_logo),
                    contentDescription = "Indiana Pacers Logo",
                    modifier = Modifier.size(32.dp)
                )
                Text(
                    text = game.awayTeam,
                    modifier = Modifier
                        .padding(start = 4.dp),
                    fontWeight = fontWeight
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(text = game.awayTeamScore.toString(), fontWeight = fontWeight)
                if (winningTeam.lowercase() == "away") {
                    Icon(
                        painter = painterResource(R.drawable.baseline_arrow_left_24),
                        contentDescription = "Arrow Left",
                    )
                }
            }
            Spacer(modifier = Modifier.height(0.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                val fontWeight = if (winningTeam.lowercase() == "home") {
                    FontWeight.Bold
                } else {
                    FontWeight.Normal
                }

                Image(
                    painter = painterResource(R.drawable.dallas_mavericks_logo),
                    contentDescription = "Dallas Mavericks Logo",
                    modifier = Modifier.size(32.dp)
                )
                Text(
                    text = game.homeTeam,
                    modifier = Modifier.padding(start = 4.dp),
                    fontWeight = fontWeight
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(text = game.homeTeamScore.toString(), fontWeight = fontWeight)
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
                .padding(start = 8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.height(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.final_status),
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "IND covered +4.5, O 236.5"
            )
        }
    }
}