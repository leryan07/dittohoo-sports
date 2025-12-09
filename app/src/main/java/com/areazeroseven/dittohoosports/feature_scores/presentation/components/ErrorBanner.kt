package com.areazeroseven.dittohoosports.feature_scores.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.areazeroseven.dittohoosports.R

@Composable
fun ErrorBanner(errorCode: Int?) {
    if (errorCode == null) return

    val errorMessage = when (errorCode) {
        429 -> stringResource(R.string.scores_error_too_many_requests)
        else -> stringResource(R.string.scores_error)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(R.color.off_white))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.baseline_warning_amber_24),
            contentDescription = stringResource(R.string.warning),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = errorMessage,
            fontSize = 14.sp
        )
    }
}
