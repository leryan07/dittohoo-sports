package com.areazeroseven.dittohoosports.matchups.domain.repository

import com.areazeroseven.dittohoosports.matchups.domain.Matchup
import com.areazeroseven.dittohoosports.util.Result
import java.time.LocalDate

interface IMatchupsRepository {
    suspend fun getNBAMatchups(
        date: LocalDate
    ): Result<List<Matchup>>
}