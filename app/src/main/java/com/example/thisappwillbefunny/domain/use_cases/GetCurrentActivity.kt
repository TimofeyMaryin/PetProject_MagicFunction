package com.example.thisappwillbefunny.domain.use_cases

import com.example.thisappwillbefunny.domain.internet.user_activity.ActivityRetrofitHelper
import com.example.thisappwillbefunny.domain.internet.user_activity.pojo.ActivityPOJO


// http://www.boredapi.com/api/activity/
object GetCurrentActivity {

    suspend fun execute(): ActivityPOJO {
        return ActivityRetrofitHelper.api.getRandomActivity().body()!!
    }

}