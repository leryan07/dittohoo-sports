package com.areazeroseven.dittohoosports.matchups.data.remote

import com.areazeroseven.dittohoosports.matchups.data.remote.dto.ScoreboardDataDto
import retrofit2.http.GET
import retrofit2.http.Query

interface IScoresApi {

    @GET("basketball/nba/scoreboard")
    suspend fun getNBAScores(
        @Query("dates") date: String
    ): ScoreboardDataDto
}