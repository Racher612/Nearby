package com.project.nearby.retrofit.models.concert

data class EmbeddedX(
    val attractions: List<com.project.nearby.retrofit.models.concert.Attraction>,
    val venues: List<com.project.nearby.retrofit.models.concert.Venue>
)