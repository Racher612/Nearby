package com.project.nearby.retrofit.models.concert


data class ClassificationX(
    val family: Boolean,
    val genre: com.project.nearby.retrofit.models.concert.Genre,
    val primary: Boolean,
    val segment: com.project.nearby.retrofit.models.concert.Segment,
    val subGenre: com.project.nearby.retrofit.models.concert.SubGenre,
    val subType: com.project.nearby.retrofit.models.concert.SubType,
    val type: com.project.nearby.retrofit.models.concert.Type
)