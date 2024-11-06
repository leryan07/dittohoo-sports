package dev.ryanle.sportsscoresandroid.feature_scores.presentation.scores

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import dev.ryanle.sportsscoresandroid.feature_scores.domain.model.Game
import dev.ryanle.sportsscoresandroid.feature_scores.presentation.scores.components.ScoreItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoresScreen(
    modifier: Modifier = Modifier
) {
    val games = listOf(
        Game(
            homeTeam = stringResource(R.string.mavericks),
            awayTeam = stringResource(R.string.pacers),
            homeTeamScore = 127,
            awayTeamScore = 134
        )
    )

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "NBA", fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
                .padding(innerPadding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_chevron_left_24),
                    contentDescription = "Chevron Left",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                TextButton(
                    onClick = {
                        // TODO
                    }
                ) {
                    Text(
                        text = "Mon, Nov 4",
                        fontSize = 18.sp
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(R.drawable.baseline_chevron_right_24),
                    contentDescription = "Chevron Right",
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn {
                items(games) { score ->
                    ScoreItem(
                        score,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}