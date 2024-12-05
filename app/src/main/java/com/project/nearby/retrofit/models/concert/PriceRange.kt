package com.project.nearby.retrofit.models.concert


data class PriceRange(
    val currency: String,
    val max: Double,
    val min: Double,
    val type: String
)