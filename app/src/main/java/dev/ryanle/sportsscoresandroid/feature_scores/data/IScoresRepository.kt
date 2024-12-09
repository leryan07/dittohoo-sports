package dev.ryanle.sportsscoresandroid.feature_scores.data

import dev.ryanle.sportsscoresandroid.feature_scores.domain.Score
import dev.ryanle.sportsscoresandroid.util.Result

interface IScoresRepository {
    suspend fun getScoresData(
        leagueId: String,
        startsAfter: String,
        startsBefore: String
    ): Result<List<Score>>
}