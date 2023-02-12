package com.example.thisappwillbefunny.domain.repository

import com.example.thisappwillbefunny.domain.room.like_activities.LikeActivitiesEntity

interface LikeActivityDaoRepo {

    fun getAllActivity(): MutableList<LikeActivitiesEntity>
    fun getLikedActivities(): MutableList<LikeActivitiesEntity>
    fun likeActivity(activity: LikeActivitiesEntity)
    fun deleteActivity(activity: LikeActivitiesEntity)
}