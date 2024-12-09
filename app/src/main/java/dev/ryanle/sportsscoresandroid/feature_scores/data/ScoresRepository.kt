package dev.ryanle.sportsscoresandroid.feature_scores.data

import dev.ryanle.sportsscoresandroid.feature_scores.data.mappers.toNbaScore
import dev.ryanle.sportsscoresandroid.feature_scores.data.remote.IScoresApi
import dev.ryanle.sportsscoresandroid.feature_scores.domain.Score
import dev.ryanle.sportsscoresandroid.util.Result
import retrofit2.HttpException
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
            val data = scoresApi.getEventsData(
                leagueId,
                startsAfter,
                startsBefore
            )

            val dataMapped = when (leagueId) {
                NBA_LEAGUE_ID -> data.toNbaScore()
                else -> null
            }

            Result.Success(
                data = dataMapped
            )
        } catch (e: HttpException) {
            e.printStackTrace()
            Result.Error(e.message ?: "An unknown error occurred.", code = e.code())
        }
    }

    companion object {
        const val NBA_LEAGUE_ID = "NBA"
    }
}