package com.project.nearby.retrofit.models.concert

import kotlinx.serialization.Serializable

@Serializable
data class Accessibility(
    val id: String,
    val info: String,
    val ticketLimit: Int
)