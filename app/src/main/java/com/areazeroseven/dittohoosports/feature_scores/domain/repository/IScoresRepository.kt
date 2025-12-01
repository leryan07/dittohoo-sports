package com.areazeroseven.dittohoosports.feature_scores.domain.repository

import com.areazeroseven.dittohoosports.feature_scores.domain.Matchup
import com.areazeroseven.dittohoosports.util.Result
import java.time.LocalDate

interface IScoresRepository {
    suspend fun getNBAScores(
        date: LocalDate
    ): Result<List<Matchup>>
}