package com.example.thisappwillbefunny.domain.room.like_activities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "activity")
data class LikeActivitiesEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "acc") val accessibility: Double,
    @ColumnInfo(name = "activity") val activity: String,
    @ColumnInfo(name = "participants") val participants: Int,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "is_like") var like: Boolean
)
