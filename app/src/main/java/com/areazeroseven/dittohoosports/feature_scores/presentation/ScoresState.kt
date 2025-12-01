package com.areazeroseven.dittohoosports.feature_scores.presentation

import com.areazeroseven.dittohoosports.feature_scores.domain.Matchup
import java.time.LocalDateTime

data class ScoresState(
    val scoresDate: LocalDateTime,
    val scoresList: List<Matchup>? = null,
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: ScoresError? = null
)

data class ScoresError(val message: String?, val code: Int?)
