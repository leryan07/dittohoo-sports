package com.areazeroseven.dittohoosports.feature_scores.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.areazeroseven.dittohoosports.R
import com.areazeroseven.dittohoosports.presentation.components.DatePickerModal
import com.areazeroseven.dittohoosports.util.DateUtil.DATE_TIME_FORMAT_EEE_MMM_d
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun ScoresDatePicker(
    date: LocalDateTime,
    onPreviousDay: () -> Unit,
    onNextDay: () -> Unit,
    onDatePickerModalDateSelected: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    var showDatePicker by remember {
        mutableStateOf(false)
    }

    val commonColor = colorScheme.secondaryContainer

    if (showDatePicker) {
        DatePickerModal(
            onDateSelected = { date ->
                date?.let { onDatePickerModalDateSelected(it) }
            },
            onDismiss = {
                showDatePicker = false
            },
            selectedDate = date.atZone(ZoneId.systemDefault()).toInstant()
                .toEpochMilli()
        )
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = onPreviousDay
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_chevron_left_24),
                contentDescription = stringResource(R.string.chevron_left_content_description),
                modifier = Modifier.size(24.dp),
                tint = commonColor
            )
        }
        TextButton(
            onClick = {
                showDatePicker = true
            },
            colors = ButtonDefaults.textButtonColors(
                contentColor = commonColor,
            )
        ) {
            Icon(
                painter = painterResource(R.drawable.outline_calendar_today_24),
                contentDescription = stringResource(R.string.calendar_content_description),
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = date.format(
                    DateTimeFormatter.ofPattern(
                        DATE_TIME_FORMAT_EEE_MMM_d
                    )
                ),
                fontSize = 18.sp
            )
        }
        IconButton(
            onClick = onNextDay
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_chevron_right_24),
                contentDescription = stringResource(R.string.chevron_right_content_description),
                modifier = Modifier.size(24.dp),
                tint = commonColor
            )
        }
    }
}