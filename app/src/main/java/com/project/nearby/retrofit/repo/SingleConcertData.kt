package com.project.nearby.retrofit.repo

import com.project.nearby.retrofit.models.concert.Concert
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SingleConcertData {
    @GET("events.json")
    suspend fun getConcertById(
        @Query("apikey") apiKey: String,
        @Query("id") id: String
    ): Response<Concert>
}