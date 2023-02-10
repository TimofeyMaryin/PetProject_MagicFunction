package com.example.thisappwillbefunny.domain.internet.user_activity

import com.example.thisappwillbefunny.domain.internet.user_activity.pojo.ActivityPOJO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ActivityAPI {


    @GET("activity/")
    suspend fun getRandomActivity(): Response<ActivityPOJO>

    @GET("activity/?{type}={detail}")
    suspend fun getActivityByFilter(@Path("type") type: String, @Path("detail") detail: String): Response<ActivityPOJO>

}