package com.example.thisappwillbefunny.domain.internet.random_cats

import com.example.thisappwillbefunny.domain.internet.random_cats.pojo.RandomCatPOJO
import retrofit2.Response
import retrofit2.http.GET

interface GetRandomCatAPI {

    @GET("meow")
    suspend fun getCats(): Response<RandomCatPOJO>

}