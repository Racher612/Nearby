package com.project.nearby.retrofit.models.concert


data class Dates(
    val spanMultipleDays: Boolean,
    val start: com.project.nearby.retrofit.models.concert.Start,
    val status: com.project.nearby.retrofit.models.concert.Status,
    val timezone: String
)