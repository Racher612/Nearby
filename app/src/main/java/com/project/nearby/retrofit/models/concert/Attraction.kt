package com.project.nearby.retrofit.models.concert

data class Attraction(
    val _links: com.project.nearby.retrofit.models.concert.Links,
    val aliases: List<String>,
    val classifications: List<com.project.nearby.retrofit.models.concert.ClassificationX>,
    val externalLinks: com.project.nearby.retrofit.models.concert.ExternalLinks,
    val id: String,
    val images: List<com.project.nearby.retrofit.models.concert.ImageXX>,
    val locale: String,
    val name: String,
    val test: Boolean,
    val type: String,
    val upcomingEvents: com.project.nearby.retrofit.models.concert.UpcomingEvents,
    val url: String
)