package com.areazeroseven.dittohoosports

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.areazeroseven.dittohoosports.matchups.presentation.ScoresScreen
import com.areazeroseven.dittohoosports.matchups.presentation.matchup_details.MatchupDetailsScreen
import kotlinx.serialization.Serializable

@Serializable
data object MatchupsList : NavKey

@Serializable
data class MatchupDetails(val id: String, val date: String) : NavKey

@Composable
fun DittohooSportsNav() {

    val backStack = rememberNavBackStack(MatchupsList)

    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<MatchupsList> {
                ScoresScreen(
                    { id, date ->
                        backStack.add(MatchupDetails(id, date))
                    }
                )
            }
            entry<MatchupDetails> { key ->
                MatchupDetailsScreen(
                    id = key.id,
                    date = key.date,
                    onBack = {
                        backStack.removeLastOrNull()
                    }
                )
            }
        },
        transitionSpec = {
            slideInHorizontally(initialOffsetX = { it }) togetherWith
                    slideOutHorizontally(targetOffsetX = { -it })
        },
        popTransitionSpec = {
            slideInHorizontally(initialOffsetX = { -it }) togetherWith
                    slideOutHorizontally(targetOffsetX = { it })
        },
    )
}