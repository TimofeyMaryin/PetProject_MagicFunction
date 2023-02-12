package com.example.thisappwillbefunny.domain.room.like_activities

import androidx.compose.runtime.MutableState
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LikeActivityDao {

    @Query("SELECT * FROM activity")
    fun getAllActivity(): MutableList<LikeActivitiesEntity>

    @Query("SELECT * FROM activity WHERE is_like = true")
    fun getLikedActivities(): MutableList<LikeActivitiesEntity>

    @Insert
    fun likeActivity(activity: LikeActivitiesEntity)

    @Delete
    fun deleteActivity(activity: LikeActivitiesEntity)


}