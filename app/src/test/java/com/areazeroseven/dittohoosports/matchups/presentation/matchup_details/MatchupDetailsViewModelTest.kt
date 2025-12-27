package com.areazeroseven.dittohoosports.matchups.presentation.matchup_details

import com.areazeroseven.dittohoosports.core.util.Result
import com.areazeroseven.dittohoosports.matchups.data.repository.MockMatchupsRepository
import com.areazeroseven.dittohoosports.matchups.domain.GameStatus
import com.areazeroseven.dittohoosports.matchups.domain.NbaTeam
import com.areazeroseven.dittohoosports.test_rules.MainDispatcherRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.time.LocalDate

class MatchupDetailsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val matchupsRepository = MockMatchupsRepository()

    private val viewModel = MatchupDetailsViewModel(matchupsRepository)

    @Test
    fun onIntent_LoadMatchupDetails_success() {
        matchupsRepository.nbaMatchupsResult = Result.Success(listOf(MockMatchupsRepository.MOCK_MATCHUP))
        matchupsRepository.nbaEventSummaryResult = Result.Success(MockMatchupsRepository.MOCK_MATCHUP_DETAILS)

        viewModel.onIntent(
            MatchupDetailsIntent.LoadMatchupDetails(
                "mockId",
                LocalDate.now().toString()
            )
        )

        with(viewModel.state) {
            assertEquals("mockId", matchup?.id)
            assertEquals(NbaTeam.PACERS, matchup?.homeTeam)
            assertEquals(NbaTeam.THUNDER, matchup?.awayTeam)
            assertEquals(100, matchup?.homeTeamScore)
            assertEquals(99, matchup?.awayTeamScore)
            assertEquals(GameStatus.Completed, matchup?.status)
        }
    }

    @Test
    fun onIntent_LoadMatchupDetails_error() {
        matchupsRepository.nbaMatchupsResult = Result.Error("Matchups Error")
        matchupsRepository.nbaEventSummaryResult = Result.Error("Event Summary Error")

        viewModel.onIntent(
            MatchupDetailsIntent.LoadMatchupDetails(
                "mockId",
                LocalDate.now().toString()
            )
        )

        with(viewModel.state) {
            assertEquals(null, matchup)
            assertEquals(null, matchupDetails)
        }
    }
}