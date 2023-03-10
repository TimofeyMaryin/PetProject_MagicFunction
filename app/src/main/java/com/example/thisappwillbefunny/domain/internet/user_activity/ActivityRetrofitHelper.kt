package com.example.thisappwillbefunny.domain.internet.user_activity

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ActivityRetrofitHelper {

    private const val BASE_URL = "https://www.boredapi.com/api/"

    private fun getInstance(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val api: ActivityAPI = getInstance().create(ActivityAPI::class.java)

}