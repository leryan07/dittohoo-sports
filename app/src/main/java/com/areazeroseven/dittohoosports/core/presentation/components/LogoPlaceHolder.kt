package com.areazeroseven.dittohoosports.core.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
private fun LogoPlaceHolder(
    modifier: Modifier = Modifier,
    size: Dp = 32.dp
) {
    Box(modifier.size(size))
}
