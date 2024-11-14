package dev.ryanle.sportsscoresandroid.feature_scores.data

import dev.ryanle.sportsscoresandroid.feature_scores.data.mappers.toNbaScore
import dev.ryanle.sportsscoresandroid.feature_scores.data.remote.IScoresApi
import dev.ryanle.sportsscoresandroid.feature_scores.domain.Score
import dev.ryanle.sportsscoresandroid.util.Result
import javax.inject.Inject

class ScoresRepository @Inject constructor(
    private val scoresApi: IScoresApi
) : IScoresRepository {

    override suspend fun getScoresData(
        leagueId: String,
        startsAfter: String,
        startsBefore: String
    ): Result<List<Score>> {
        return try {
            Result.Success(
                data = scoresApi.getEventsData(
                    leagueId,
                    startsAfter,
                    startsBefore
                ).toNbaScore()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(e.message ?: "An unknown error occurred.")
        }
    }

    companion object {
        const val NBA_LEAGUE_ID = "NBA"
    }
}