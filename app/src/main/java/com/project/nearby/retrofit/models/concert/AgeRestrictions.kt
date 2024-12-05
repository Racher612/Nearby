package com.project.nearby.retrofit.models.concert

import kotlinx.serialization.Serializable

@Serializable
data class AgeRestrictions(
    val id: String,
    val legalAgeEnforced: Boolean
)