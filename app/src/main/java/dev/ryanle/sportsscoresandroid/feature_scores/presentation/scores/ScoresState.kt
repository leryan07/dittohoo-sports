package dev.ryanle.sportsscoresandroid.feature_scores.presentation.scores

import dev.ryanle.sportsscoresandroid.feature_scores.domain.Score
import java.time.LocalDateTime

data class ScoresState(
    val scoresDate: LocalDateTime,
    val scoresList: List<Score>? = null,
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: ScoresError? = null
)

data class ScoresError(val message: String?, val code: Int?)
