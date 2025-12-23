package com.areazeroseven.dittohoosports.core.data.remote

import com.areazeroseven.dittohoosports.core.data.remote.dto.ScoreboardDataDTO
import com.areazeroseven.dittohoosports.core.data.remote.dto.SummaryEventDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface IEspnApi {

    @GET("basketball/nba/scoreboard")
    suspend fun getNBAScores(
        @Query("dates") date: String
    ): ScoreboardDataDTO

    @GET("basketball/nba/summary")
    suspend fun getNBAEventSummary(
        @Query("event") event: String
    ): SummaryEventDTO
}