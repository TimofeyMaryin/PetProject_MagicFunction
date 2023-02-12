package com.example.thisappwillbefunny.domain.internet.user_activity.pojo

import com.example.thisappwillbefunny.domain.model.ActivityPojoSmall

data class ActivityPOJO(
    val accessibility: Double,
    val activity: String,
    val key: String,
    val link: String,
    val participants: Int,
    val price: Double,
    val type: String
) {
    fun reduction(): ActivityPojoSmall =
        ActivityPojoSmall(
            accessibility = this.accessibility,
            activity = this.activity,
            participants = this.participants,
            price = this.price,
            type = this.type
        )
}