package com.example.thisappwillbefunny.domain.model

import com.example.thisappwillbefunny.domain.internet.user_activity.pojo.ActivityPOJO
import java.lang.reflect.AccessibleObject

data class RequestActivityModel(
    val activityPOJO: ActivityPOJO,
    val accessibleModel: ActivityItemDescModel,
    val participants: ActivityItemDescModel,
    val pricing: ActivityItemDescModel

)
