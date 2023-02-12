package com.example.thisappwillbefunny.domain.repository

import android.app.Application
import com.example.thisappwillbefunny.domain.room.AppDataBase
import com.example.thisappwillbefunny.domain.room.like_activities.LikeActivitiesEntity

class LikeActivityRepository(application: Application):  LikeActivityDaoRepo {

    private val dao = AppDataBase.getInstance(application = application).likeActivity()
    override fun getAllActivity(): MutableList<LikeActivitiesEntity>  = dao.getAllActivity()

    override suspend fun getLikedActivities(): MutableList<LikeActivitiesEntity> = dao.getLikedActivities()

    override fun likeActivity(activity: LikeActivitiesEntity) = dao.likeActivity(activity = activity)

    override suspend fun deleteActivity(activity: LikeActivitiesEntity) = dao.deleteActivity(activity = activity)
}