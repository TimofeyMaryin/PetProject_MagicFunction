package com.example.thisappwillbefunny.domain.internet.random_cats

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RandomCatsRetrofitHelper {
    private const val BASE_URL = "https://aws.random.cat/"

    private fun getInstance(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    val api: GetRandomCatAPI = getInstance().create(GetRandomCatAPI::class.java)
}