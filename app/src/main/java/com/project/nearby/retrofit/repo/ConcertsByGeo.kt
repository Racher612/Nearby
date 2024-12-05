package com.project.nearby.retrofit.repo

import com.project.nearby.retrofit.models.concert.Concert
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ConcertsByGeo {
    @GET("events.json")
    suspend fun concertsByGeo(
        @Query("apikey") apiKey: String,
        @Query("geoPoint") geoPoint: String
    ): Response<Concert>
}