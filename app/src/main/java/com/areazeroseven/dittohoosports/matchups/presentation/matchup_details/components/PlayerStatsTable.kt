package com.areazeroseven.dittohoosports.matchups.presentation.matchup_details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.areazeroseven.dittohoosports.R
import com.areazeroseven.dittohoosports.matchups.domain.PlayerStatColumn
import com.areazeroseven.dittohoosports.matchups.domain.PlayerStats

@Composable
fun PlayerStatsTable(
    teamPlayerStats: List<PlayerStats>,
    statColumns: List<PlayerStatColumn>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        StatsRow(
            name = stringResource(R.string.stat_name_label),
            values = statColumns.map { it.label },
            nameFontWeight = FontWeight.Bold,
            valueFontWeight = FontWeight.Bold,
            nameFontSize = 12.sp,
            valueFontSize = 12.sp
        )
        teamPlayerStats.forEachIndexed { index, player ->
            StatsRow(
                name = player.shortName,
                values = statColumns.map { player.statsMap[it] ?: "" },
                nameFontSize = 10.sp,
                valueFontSize = 10.sp
            )
            if (index < teamPlayerStats.lastIndex) {
                HorizontalDivider()
            }
        }
    }
}

@Composable
private fun StatsRow(
    name: String,
    values: List<String>,
    nameFontWeight: FontWeight = FontWeight.Normal,
    valueFontWeight: FontWeight = FontWeight.Normal,
    nameFontSize: TextUnit,
    valueFontSize: TextUnit
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = name,
            fontSize = nameFontSize,
            fontWeight = nameFontWeight,
            modifier = Modifier
                .width(64.dp)
                .padding(start = 16.dp)
        )
        values.forEach { value ->
            Text(
                text = value,
                fontSize = valueFontSize,
                fontWeight = valueFontWeight,
                textAlign = TextAlign.Center,
                modifier = Modifier.width(28.dp)
            )
        }
    }
}
