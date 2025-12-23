package com.areazeroseven.dittohoosports.matchups.domain.repository

import com.areazeroseven.dittohoosports.matchups.domain.Matchup
import com.areazeroseven.dittohoosports.matchups.domain.MatchupDetails
import com.areazeroseven.dittohoosports.core.util.Result
import java.time.LocalDate

interface IMatchupsRepository {
    suspend fun getNBAMatchups(
        date: LocalDate
    ): Result<List<Matchup>>

    suspend fun getNBAEventSummary(
        eventId: String
    ): Result<MatchupDetails>
}