package com.project.nearby.retrofit.models.concert

data class Event(
    val _embedded: EmbeddedX,
    val _links: LinksXX,
    val accessibility: Accessibility,
    val ageRestrictions: com.project.nearby.retrofit.models.concert.AgeRestrictions,
    val classifications: List<com.project.nearby.retrofit.models.concert.ClassificationX>,
    val dates: com.project.nearby.retrofit.models.concert.Dates,
    val id: String,
    val images: List<com.project.nearby.retrofit.models.concert.ImageXX>,

    //deprecated for displayment, API does not provide this item
    val info: String?,

    val locale: String,
    val name: String,
    val pleaseNote: String,
    val priceRanges: List<com.project.nearby.retrofit.models.concert.PriceRange>,
    val products: List<com.project.nearby.retrofit.models.concert.Product>,
    val promoter: com.project.nearby.retrofit.models.concert.Promoter,
    val promoters: List<com.project.nearby.retrofit.models.concert.Promoter>,
    val sales: com.project.nearby.retrofit.models.concert.Sales,
    val seatmap: com.project.nearby.retrofit.models.concert.Seatmap,
    val test: Boolean,
    val ticketLimit: com.project.nearby.retrofit.models.concert.TicketLimit?,
    val ticketing: com.project.nearby.retrofit.models.concert.Ticketing,
    val type: String,
    val url: String
)