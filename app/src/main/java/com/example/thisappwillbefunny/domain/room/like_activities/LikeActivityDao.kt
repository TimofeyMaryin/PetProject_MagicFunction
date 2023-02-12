package com.example.thisappwillbefunny.domain.room.like_activities

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LikeActivityDao {

    @Query("select * from activity")
    fun getAllActivity(): MutableList<LikeActivitiesEntity>

    @Query("SELECT * FROM activity WHERE is_like = true")
    suspend fun getLikedActivities(): MutableList<LikeActivitiesEntity>

    @Insert
    suspend fun likeActivity(activity: LikeActivitiesEntity)

    @Delete
    suspend fun deleteActivity(activity: LikeActivitiesEntity)


}