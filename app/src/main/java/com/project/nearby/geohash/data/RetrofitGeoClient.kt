package com.project.nearby.geohash.data

import com.project.nearby.retrofit.models.concert.Concert
import com.project.nearby.retrofit.repo.ConcertsByGeo
import com.project.nearby.retrofit.repo.WebData
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitGeoClient {
    private fun getClient(): Retrofit =
        Retrofit.Builder()
            .baseUrl(WebData.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    suspend fun getConcertsByGeo(geoPoint: String) : Response<Concert> {
        val apiManager = getClient().create(ConcertsByGeo::class.java)
        val result = apiManager.concertsByGeo(
            apiKey = WebData.apiKey,
            geoPoint = geoPoint
        )
        return result
    }
}