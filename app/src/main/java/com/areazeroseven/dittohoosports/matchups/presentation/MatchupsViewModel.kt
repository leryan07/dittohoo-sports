package com.areazeroseven.dittohoosports.matchups.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.areazeroseven.dittohoosports.matchups.domain.repository.IMatchupsRepository
import com.areazeroseven.dittohoosports.core.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class MatchupsViewModel @Inject constructor(
    private val matchupsRepository: IMatchupsRepository
) : ViewModel() {

    var state: MatchupsState by mutableStateOf(
        MatchupsState(
            date = LocalDateTime.now().with(LocalTime.MIDNIGHT)
        )
    )
        private set

    init {
        fetchScores(state.date, isRefresh = false)
    }

    fun fetchNextDay() {
        val nextDay = state.date.plusDays(1)
        fetchScores(nextDay, isRefresh = false)
    }

    fun fetchPreviousDay() {
        val prevDay = state.date.minusDays(1)
        fetchScores(prevDay, isRefresh = false)
    }

    fun onSelectedDate(selectedDateMillis: Long) {
        val utcDate =
            Instant.ofEpochMilli(selectedDateMillis).atZone(ZoneId.of("UTC")).toLocalDate()

        // Convert that LocalDate to LocalDateTime at start of day in local timezone
        val localDateTime = utcDate.atStartOfDay(ZoneId.systemDefault()).toLocalDateTime()
        fetchScores(localDateTime, isRefresh = false)
    }

    fun refresh() {
        fetchScores(state.date, isRefresh = true)
    }

    private fun fetchScores(date: LocalDateTime, isRefresh: Boolean) {
        viewModelScope.launch {
            state = state.copy(
                date = date,
                isLoading = !isRefresh,
                isRefreshing = isRefresh
            )

            val result = matchupsRepository.getNBAMatchups(
                date.toLocalDate()
            )

            state = when (result) {
                is Result.Error -> {
                    state.copy(
                        error = MatchupsError(result.message, result.code),
                        isLoading = false,
                        isRefreshing = false,
                        list = if (isRefresh) state.list else null
                    )
                }

                is Result.Success -> {
                    state.copy(
                        list = result.data ?: emptyList(),
                        isLoading = false,
                        isRefreshing = false,
                        error = null
                    )
                }
            }
        }
    }
}