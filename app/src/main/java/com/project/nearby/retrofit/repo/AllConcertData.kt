package com.project.nearby.retrofit.repo

import com.project.nearby.retrofit.models.concert.Concert
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AllConcertData {
    @GET("events.json")
    suspend fun getAllConcerts (
        @Query("apikey") apiKey : String
    ): Response<Concert>
}