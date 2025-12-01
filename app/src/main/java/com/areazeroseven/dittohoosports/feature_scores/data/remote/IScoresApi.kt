package com.areazeroseven.dittohoosports.feature_scores.data.remote

import com.areazeroseven.dittohoosports.feature_scores.data.remote.dto.ScoreboardDataDto
import retrofit2.http.GET
import retrofit2.http.Query

interface IScoresApi {

    @GET("basketball/nba/scoreboard")
    suspend fun getNBAScores(
        @Query("dates") date: String
    ): ScoreboardDataDto
}