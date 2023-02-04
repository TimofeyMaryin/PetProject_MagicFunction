package com.example.thisappwillbefunny.domain.internet.user_activity

import com.example.thisappwillbefunny.domain.internet.user_activity.pojo.ActivityPOJO
import retrofit2.Response
import retrofit2.http.GET

interface ActivityAPI {


    @GET
    suspend fun getRandomActivity(): Response<ActivityPOJO>

}