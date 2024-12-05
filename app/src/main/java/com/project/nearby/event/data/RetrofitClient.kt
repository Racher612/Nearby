package com.project.nearby.event.data

import android.util.Log
import com.project.nearby.retrofit.repo.SingleConcertData
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

    suspend fun getConcertById(id : String) : Response<Concert> {
        Log.d("LOAD", "loaded event")
        val apiManager = getClient().create(SingleConcertData::class.java)
        val result = apiManager.getConcertById(
            apiKey = WebData.apiKey,
            id = id
        )
        return result
    }
}
