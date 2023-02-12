package com.example.thisappwillbefunny.domain.repository

import com.example.thisappwillbefunny.domain.room.like_activities.LikeActivitiesEntity

interface LikeActivityDaoRepo {

    fun getAllActivity(): MutableList<LikeActivitiesEntity>
    suspend fun getLikedActivities(): MutableList<LikeActivitiesEntity>
    fun likeActivity(activity: LikeActivitiesEntity)
    suspend fun deleteActivity(activity: LikeActivitiesEntity)
}