package com.areazeroseven.dittohoosports.matchups.data.remote.dto

data class EventStatusDTO(
    val displayClock: String,
    val period: Int,
    val type: StatusTypeDTO
)

data class StatusTypeDTO(
    val name: String,
    val state: String,
    val completed: Boolean,
    val description: String,
    val shortDetail: String
)
