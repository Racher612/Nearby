package com.project.nearby.retrofit.models.concert


data class Ticketing(
    val allInclusivePricing: com.project.nearby.retrofit.models.concert.AllInclusivePricing,
    val id: String,
    val safeTix: com.project.nearby.retrofit.models.concert.SafeTix
)