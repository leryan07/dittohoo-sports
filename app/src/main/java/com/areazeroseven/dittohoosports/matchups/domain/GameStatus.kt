package com.areazeroseven.dittohoosports.matchups.domain

import java.time.LocalDateTime

sealed class GameStatus {
    data class Scheduled(val startsAt: LocalDateTime?) : GameStatus()
    data class Live(val clock: String) : GameStatus()
    data object Completed : GameStatus()
}