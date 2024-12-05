package com.project.nearby.main.data

import com.project.nearby.retrofit.repo.AllConcertData
import com.project.nearby.retrofit.repo.WebData
import com.project.nearby.retrofit.models.concert.Concert
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private fun getClient(): Retrofit =
        Retrofit.Builder()
            .baseUrl(WebData.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    suspend fun getAllConcerts() : Response<Concert>{
        val apiManager = getClient().create(AllConcertData::class.java)
        val result = apiManager.getAllConcerts(
            apiKey = WebData.apiKey
        )
        return result
    }
}