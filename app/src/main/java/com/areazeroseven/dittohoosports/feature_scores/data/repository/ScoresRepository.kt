package com.areazeroseven.dittohoosports.feature_scores.data.repository

import com.areazeroseven.dittohoosports.feature_scores.data.mappers.toNBAMatchup
import com.areazeroseven.dittohoosports.feature_scores.data.remote.IScoresApi
import com.areazeroseven.dittohoosports.feature_scores.domain.Matchup
import com.areazeroseven.dittohoosports.feature_scores.domain.repository.IScoresRepository
import com.areazeroseven.dittohoosports.util.DateUtil
import com.areazeroseven.dittohoosports.util.Result
import retrofit2.HttpException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class ScoresRepository @Inject constructor(
    private val scoresApi: IScoresApi
) : IScoresRepository {

    override suspend fun getNBAScores(
        date: LocalDate
    ): Result<List<Matchup>> {
        return try {
            val formattedDate = date.format(DateTimeFormatter.ofPattern(
                DateUtil.DATE_TIME_FORMAT_yyyyMMdd
            ))
            val data = scoresApi.getNBAScores(
                formattedDate
            ).toNBAMatchup()

            Result.Success(data)
        } catch (e: HttpException) {
            e.printStackTrace()
            Result.Error(
                e.message ?: "An unknown error occurred.",
                e.code()
            )
        }
    }
}