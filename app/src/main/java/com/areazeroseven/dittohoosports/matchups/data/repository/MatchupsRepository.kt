package com.areazeroseven.dittohoosports.matchups.data.repository

import com.areazeroseven.dittohoosports.matchups.data.mappers.toNBAMatchup
import com.areazeroseven.dittohoosports.matchups.data.mappers.toNBAMatchupDetails
import com.areazeroseven.dittohoosports.core.data.remote.IEspnApi
import com.areazeroseven.dittohoosports.matchups.domain.Matchup
import com.areazeroseven.dittohoosports.matchups.domain.MatchupDetails
import com.areazeroseven.dittohoosports.matchups.domain.repository.IMatchupsRepository
import com.areazeroseven.dittohoosports.core.util.DateUtil
import com.areazeroseven.dittohoosports.core.util.Result
import retrofit2.HttpException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class MatchupsRepository @Inject constructor(
    private val scoresApi: IEspnApi
) : IMatchupsRepository {

    override suspend fun getNBAMatchups(
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

    override suspend fun getNBAEventSummary(eventId: String): Result<MatchupDetails> {
        return try {
            val data = scoresApi.getNBAEventSummary(eventId)
                .toNBAMatchupDetails()

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