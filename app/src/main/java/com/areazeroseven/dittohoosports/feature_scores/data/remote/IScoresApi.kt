package com.areazeroseven.dittohoosports.feature_scores.data.remote

import com.areazeroseven.dittohoosports.feature_scores.data.remote.dto.ScoreboardDataDto
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.LocalDate
import java.util.Date

interface IScoresApi {

    @GET("basketball/nba/scoreboard")
    suspend fun getNBAScores(
        @Query("dates") date: String
    ): ScoreboardDataDto
}