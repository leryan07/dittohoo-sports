package com.areazeroseven.dittohoosports.matchups.data.remote.dto

data class EventStatusDto(
    val displayClock: String,
    val period: Int,
    val type: StatusTypeDto
)

data class StatusTypeDto(
    val name: String,
    val state: String,
    val completed: Boolean,
    val description: String,
    val shortDetail: String
)
