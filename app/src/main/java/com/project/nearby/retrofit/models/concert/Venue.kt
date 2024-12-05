package com.project.nearby.retrofit.models.concert

data class Venue(
    val _links: com.project.nearby.retrofit.models.concert.Links,
    val accessibleSeatingDetail: String,
    val ada: com.project.nearby.retrofit.models.concert.Ada,
    val address: com.project.nearby.retrofit.models.concert.Address,
    val aliases: List<String>,
    val boxOfficeInfo: com.project.nearby.retrofit.models.concert.BoxOfficeInfo,
    val city: com.project.nearby.retrofit.models.concert.City,
    val country: com.project.nearby.retrofit.models.concert.Country,
    val dmas: List<com.project.nearby.retrofit.models.concert.Dma>,
    val generalInfo: com.project.nearby.retrofit.models.concert.GeneralInfo,
    val id: String,
    val images: List<com.project.nearby.retrofit.models.concert.ImageXX>,
    val locale: String,
    val location: com.project.nearby.retrofit.models.concert.Location,
    val markets: List<com.project.nearby.retrofit.models.concert.Market>,
    val name: String,
    val parkingDetail: String,
    val postalCode: String,
    val social: com.project.nearby.retrofit.models.concert.Social,
    val state: com.project.nearby.retrofit.models.concert.State,
    val test: Boolean,
    val timezone: String,
    val type: String,
    val upcomingEvents: com.project.nearby.retrofit.models.concert.UpcomingEventsX,
    val url: String
)