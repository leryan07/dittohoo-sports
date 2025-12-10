package com.areazeroseven.dittohoosports.matchups.presentation.matchup_details

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.areazeroseven.dittohoosports.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchupDetailsScreen(
    id: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = onBack
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.arrow_back_24),
                            contentDescription = stringResource(R.string.arrow_back)
                        )
                    }
                }
            )
        }
    ) {

    }
}