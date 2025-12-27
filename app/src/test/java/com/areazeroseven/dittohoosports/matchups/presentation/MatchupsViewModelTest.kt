package com.areazeroseven.dittohoosports.matchups.presentation

import com.areazeroseven.dittohoosports.core.util.Result
import com.areazeroseven.dittohoosports.matchups.data.repository.MockMatchupsRepository
import com.areazeroseven.dittohoosports.test_rules.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.LocalDateTime
import java.time.ZoneOffset

@OptIn(ExperimentalCoroutinesApi::class)
class MatchupsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val matchupsRepository = MockMatchupsRepository()

    private lateinit var viewModel: MatchupsViewModel

    @Before
    fun setup() {
        viewModel = MatchupsViewModel(
            matchupsRepository = matchupsRepository
        )
        assertEquals(0, viewModel.state.list?.size)
    }

    @Test
    fun fetchNextDay_success() = runTest {
        matchupsRepository.nbaMatchupsResult = Result.Success(listOf(MockMatchupsRepository.MOCK_MATCHUP))

        val nextDay = viewModel.state.date.plusDays(1)
        viewModel.fetchNextDay()

        assertEquals(nextDay, viewModel.state.date)
        assertEquals(1, viewModel.state.list?.size)
    }

    @Test
    fun fetchNextDay_error() = runTest {
        matchupsRepository.nbaMatchupsResult = Result.Error("NBA Matchups Error")

        viewModel.fetchNextDay()
        assertEquals("NBA Matchups Error", viewModel.state.error?.message)
        assertEquals(null, viewModel.state.list)
    }

    @Test
    fun fetchPreviousDay_success() = runTest {
        matchupsRepository.nbaMatchupsResult = Result.Success(listOf(MockMatchupsRepository.MOCK_MATCHUP))

        val previousDay = viewModel.state.date.minusDays(1)
        viewModel.fetchPreviousDay()

        assertEquals(previousDay, viewModel.state.date)
        assertEquals(1, viewModel.state.list?.size)
    }

    @Test
    fun fetchPreviousDay_error() = runTest {
        matchupsRepository.nbaMatchupsResult = Result.Error("NBA Matchups Error")

        viewModel.fetchPreviousDay()
        assertEquals("NBA Matchups Error", viewModel.state.error?.message)
        assertEquals(null, viewModel.state.list)
    }

    @Test
    fun onSelectedDate_success() = runTest {
        val date = LocalDateTime.of(2025, 12, 27, 0, 0)

        matchupsRepository.nbaMatchupsResult = Result.Success(listOf(MockMatchupsRepository.MOCK_MATCHUP))
        viewModel.onSelectedDate(date.toInstant(ZoneOffset.UTC).toEpochMilli())

        assertEquals(date, viewModel.state.date)
        assertEquals(1, viewModel.state.list?.size)
    }

    @Test
    fun refresh_success() = runTest {
        val date = viewModel.state.date
        viewModel.refresh()

        // Date shouldn't have changed
        assertEquals(date, viewModel.state.date)
        assertEquals(0, viewModel.state.list?.size)
    }

    @Test
    fun refresh_error() = runTest {
        val date = viewModel.state.date

        matchupsRepository.nbaMatchupsResult = Result.Error("NBA Matchups Error")
        viewModel.refresh()

        // Date shouldn't have changed
        assertEquals(date, viewModel.state.date)
        assertEquals(0, viewModel.state.list?.size)
        assertEquals("NBA Matchups Error", viewModel.state.error?.message)
    }
}