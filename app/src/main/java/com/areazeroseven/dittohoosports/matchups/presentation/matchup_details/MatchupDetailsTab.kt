package com.areazeroseven.dittohoosports.matchups.presentation.matchup_details

import com.areazeroseven.dittohoosports.R
import com.areazeroseven.dittohoosports.core.presentation.util.UiText
import com.areazeroseven.dittohoosports.core.presentation.util.UiText.StringResource

enum class MatchupDetailsTab(val label: UiText) {
    GAME(StringResource(R.string.game)),
    STATS(StringResource(R.string.stats)),
    ODDS(StringResource(R.string.odds))
}