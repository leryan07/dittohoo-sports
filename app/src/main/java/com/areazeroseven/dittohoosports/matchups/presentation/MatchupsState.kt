package com.areazeroseven.dittohoosports.matchups.presentation

import com.areazeroseven.dittohoosports.matchups.domain.Matchup
import java.time.LocalDateTime

data class MatchupsState(
    val date: LocalDateTime,
    val list: List<Matchup>? = null,
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: MatchupsError? = null
)

data class MatchupsError(val message: String?, val code: Int?)
