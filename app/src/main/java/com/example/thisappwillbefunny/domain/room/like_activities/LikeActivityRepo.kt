package com.example.thisappwillbefunny.domain.room.like_activities

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

interface LikeActivityRepo {

    fun getAllActivity(): MutableList<LikeActivitiesEntity>
    suspend fun getLikedActivities(): MutableList<LikeActivitiesEntity>
    fun likeActivity(activity: LikeActivitiesEntity)
    suspend fun deleteActivity(activity: LikeActivitiesEntity)
}