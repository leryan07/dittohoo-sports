package com.areazeroseven.dittohoosports.matchups.domain

data class PlayerStats(
    val shortName: String,
    val statsMap: Map<PlayerStatColumn, String>
)

enum class PlayerStatColumn(val label: String) {
    MINUTES("Min"),
    POINTS("Pts"),
    FIELD_GOALS("FG"),
    THREE_POINT_FIELD_GOALS("3PT"),
    FREE_THROWS("FT"),
    REBOUNDS("Reb"),
    ASSISTS("Ast"),
    TURNOVERS("TO"),
    STEALS("Stl"),
    BLOCKS("Blk"),
    OFFENSIVE_REBOUNDS("OReb"),
    DEFENSIVE_REBOUNDS("DReb"),
    FOULS("PF"),
    PLUS_MINUS("+/-")
}
