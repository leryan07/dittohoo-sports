package com.areazeroseven.dittohoosports.matchups.presentation.matchup_details

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetailsTeamRow(
    @DrawableRes logoResId: Int,
    abbreviation: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painterResource(logoResId),
            contentDescription = "",
            modifier = Modifier.size(64.dp)
        )
        Text(
            text = abbreviation,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        )
    }
}