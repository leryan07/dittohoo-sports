package dev.ryanle.sportsscoresandroid.feature_scores.presentation.scores

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ryanle.sportsscoresandroid.feature_scores.data.IScoresRepository
import dev.ryanle.sportsscoresandroid.feature_scores.data.ScoresRepository.Companion.NBA_LEAGUE_ID
import dev.ryanle.sportsscoresandroid.util.DateUtil
import dev.ryanle.sportsscoresandroid.util.Result
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class ScoresViewModel @Inject constructor(
    private val repository: IScoresRepository
) : ViewModel() {

    var state: ScoresState by mutableStateOf(
        ScoresState(
            scoresDate = LocalDateTime.now().with(LocalTime.MIDNIGHT)
        )
    )
        private set

    init {
        fetchScores(state.scoresDate, isRefresh = false)
    }

    fun fetchNextDay() {
        val nextDay = state.scoresDate.plusDays(1)
        fetchScores(nextDay, isRefresh = false)
    }

    fun fetchPreviousDay() {
        val prevDay = state.scoresDate.minusDays(1)
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
        fetchScores(state.scoresDate, isRefresh = true)
    }

    private fun fetchScores(date: LocalDateTime, isRefresh: Boolean) {
        val estZone = ZoneId.of(DateUtil.NEW_YORK_TIME_ZONE_ID)
        val estDateTime = ZonedDateTime.of(date, estZone)
        val estFormattedStart = estDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        val estDateTimeEnd = estDateTime.plusDays(1)
        val estFormattedEnd = estDateTimeEnd.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)

        viewModelScope.launch {
            state = state.copy(
                scoresDate = date,
                isLoading = !isRefresh,
                isRefreshing = isRefresh
            )

            val result = repository.getScoresData(
                leagueId = NBA_LEAGUE_ID,
                startsAfter = estFormattedStart.toString(),
                startsBefore = estFormattedEnd.toString()
            )

            state = when (result) {
                is Result.Error -> {
                    state.copy(
                        error = ScoresError(result.message, result.code),
                        isLoading = false,
                        isRefreshing = false,
                        scoresList = if (isRefresh) state.scoresList else null
                    )
                }

                is Result.Success -> {
                    state.copy(
                        scoresList = result.data ?: emptyList(),
                        isLoading = false,
                        isRefreshing = false,
                        error = null
                    )
                }
            }
        }
    }
}