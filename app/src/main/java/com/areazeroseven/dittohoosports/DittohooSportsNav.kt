package com.areazeroseven.dittohoosports

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.areazeroseven.dittohoosports.matchups.presentation.ScoresScreen
import com.areazeroseven.dittohoosports.matchups.presentation.matchup_details.MatchupDetailsScreen
import kotlinx.serialization.Serializable

@Serializable
data object MatchupsList : NavKey

@Serializable
data class MatchupDetails(val id: String) : NavKey

@Composable
fun DittohooSportsNav() {

    val backStack = rememberNavBackStack(MatchupsList)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<MatchupsList> {
                ScoresScreen(
                    { id ->
                        backStack.add(MatchupDetails(id))
                    }
                )
            }
            entry<MatchupDetails> { key ->
                MatchupDetailsScreen(
                    id = key.id,
                    onBack = {
                        backStack.removeLastOrNull()
                    }
                )
            }
        }
    )
}