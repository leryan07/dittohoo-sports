package com.areazeroseven.dittohoosports.matchups.data.repository

import com.areazeroseven.dittohoosports.core.util.Result
import com.areazeroseven.dittohoosports.matchups.domain.GameStatus
import com.areazeroseven.dittohoosports.matchups.domain.Matchup
import com.areazeroseven.dittohoosports.matchups.domain.MatchupDetails
import com.areazeroseven.dittohoosports.matchups.domain.NbaTeam
import com.areazeroseven.dittohoosports.matchups.domain.repository.IMatchupsRepository
import java.time.LocalDate

class MockMatchupsRepository : IMatchupsRepository {

    var nbaMatchupsResult: Result<List<Matchup>> = Result.Success(emptyList())
    var nbaEventSummaryResult: Result<MatchupDetails> = Result.Success(MOCK_MATCHUP_DETAILS)

    override suspend fun getNBAMatchups(date: LocalDate): Result<List<Matchup>> {
        return nbaMatchupsResult
    }

    override suspend fun getNBAEventSummary(eventId: String): Result<MatchupDetails> {
        return nbaEventSummaryResult
    }

    companion object {
        val MOCK_MATCHUP = Matchup(
            id = "mockId",
            homeTeam = NbaTeam.PACERS,
            awayTeam = NbaTeam.THUNDER,
            homeTeamScore = 100,
            awayTeamScore = 99,
            status = GameStatus.Completed,
        )

        val MOCK_MATCHUP_DETAILS = MatchupDetails(
            awayTeam = NbaTeam.THUNDER,
            homeTeam = NbaTeam.PACERS,
            awayTeamAbbreviation = "OKC",
            homeTeamAbbreviation = "IND",
            awayTeamScore = "99",
            homeTeamScore = "100",
            awayTeamPlayerStats = emptyList(),
            homeTeamPlayerStats = emptyList()
        )
    }
}