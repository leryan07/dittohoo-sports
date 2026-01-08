package com.areazeroseven.dittohoosports.matchups.presentation.matchup_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.areazeroseven.dittohoosports.matchups.domain.Matchup
import com.areazeroseven.dittohoosports.matchups.domain.MatchupDetails
import com.areazeroseven.dittohoosports.matchups.domain.repository.IMatchupsRepository
import com.areazeroseven.dittohoosports.core.util.Result
import com.areazeroseven.dittohoosports.matchups.presentation.matchup_details.MatchupDetailsIntent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MatchupDetailsViewModel @Inject constructor(
    private val matchupsRepository: IMatchupsRepository
) : ViewModel() {

    var state by mutableStateOf(MatchupDetailsState())
        private set

    fun onIntent(intent: MatchupDetailsIntent) {
        when (intent) {
            is LoadMatchupDetails -> {
                fetchMatchupDetails(intent.id, intent.date, false)
            }

            is OnTabSelected -> {
                state = state.copy(selectedDestination = intent.index)
            }

            is OnTeamStatsSelected -> {
                state = state.copy(selectedTeamStatsIndex = intent.index)
            }
        }
    }

    private fun fetchMatchupDetails(id: String, date: String, isRefresh: Boolean) {
        viewModelScope.launch {
            state = if (isRefresh) {
                state.copy(isLoading = false, isRefreshing = true)
            } else {
                state.copy(isLoading = true, isRefreshing = false)
            }

            var matchup: Matchup?
            val matchupResult = matchupsRepository.getNBAMatchups(
                LocalDate.parse(date)
            )

            when (matchupResult) {
                is Result.Success -> {
                    matchup = matchupResult.data?.find { it.id == id }
                }

                is Result.Error -> {
                    matchup = null
                }
            }

            var matchupDetails: MatchupDetails?
            val result = matchupsRepository.getNBAEventSummary(id)

            when (result) {
                is Result.Success -> {
                    matchupDetails = result.data
                }

                is Result.Error -> {
                    matchupDetails = null
                }
            }

            state = state.copy(
                matchup = matchup,
                matchupDetails = matchupDetails,
                isLoading = false,
                isRefreshing = false
            )
        }
    }
}