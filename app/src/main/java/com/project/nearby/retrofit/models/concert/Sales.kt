package com.project.nearby.retrofit.models.concert


data class Sales(
    val presales: List<com.project.nearby.retrofit.models.concert.Presale>,
    val `public`: com.project.nearby.retrofit.models.concert.Public
)