package com.project.nearby.retrofit.models.concert

data class ExternalLinks(
    val facebook: List<com.project.nearby.retrofit.models.concert.Facebook>,
    val homepage: List<com.project.nearby.retrofit.models.concert.Homepage>,
    val instagram: List<com.project.nearby.retrofit.models.concert.Instagram>,
    val twitter: List<com.project.nearby.retrofit.models.concert.Twitter>,
    val wiki: List<com.project.nearby.retrofit.models.concert.Wiki>
)