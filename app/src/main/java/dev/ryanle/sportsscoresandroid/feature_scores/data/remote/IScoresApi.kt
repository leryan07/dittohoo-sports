package dev.ryanle.sportsscoresandroid.feature_scores.data.remote

import dev.ryanle.sportsscoresandroid.feature_scores.data.remote.dto.EventsDataDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface IScoresApi {

    @Headers("x-api-key: 40ba6eafa7b2f86dff6345d5b06b22ef")
    @GET("/v1/events")
    suspend fun getEventsData(
        @Query("leagueID") leagueId: String,
        @Query("startsAfter") startsAfter: String,
        @Query("startsBefore") startsBefore: String
    ): EventsDataDto
}