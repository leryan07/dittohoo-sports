package com.areazeroseven.dittohoosports.matchups.data.mappers

import com.areazeroseven.dittohoosports.core.data.remote.dto.PlayersDTO
import com.areazeroseven.dittohoosports.matchups.domain.PlayerStatColumn
import com.areazeroseven.dittohoosports.matchups.domain.PlayerStats

fun PlayersDTO.toPlayerStats(): List<PlayerStats> {
    return this.statistics.flatMap { statistics ->
        statistics.athletes.map { athletes ->
            val stats = athletes.stats

            PlayerStats(
                shortName = athletes.athlete.shortName,
                statsMap = PlayerStatColumn.entries.toTypedArray().zip(stats).toMap()
            )
        }
    }
}