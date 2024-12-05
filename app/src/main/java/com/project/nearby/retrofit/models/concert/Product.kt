package com.project.nearby.retrofit.models.concert

data class Product(
    val classifications: List<com.project.nearby.retrofit.models.concert.ClassificationX>,
    val id: String,
    val name: String,
    val type: String,
    val url: String
)