package com.example.thisappwillbefunny.domain.room

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.thisappwillbefunny.domain.room.like_activities.LikeActivitiesEntity
import com.example.thisappwillbefunny.domain.room.like_activities.LikeActivityDao
import com.example.thisappwillbefunny.domain.room.show_tips.ShowTips
import com.example.thisappwillbefunny.domain.room.show_tips.ShowTipsDao


@Database(
    entities = [
        ShowTips::class
    ],
    version = 1
)
abstract class AppDataBase: RoomDatabase() {
    abstract fun tipsDao(): ShowTipsDao
    abstract fun likeActivity(): LikeActivityDao


    companion object {
        @Volatile var INSTANCE: AppDataBase? = null

        fun getInstance(application: Application): AppDataBase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        application,
                        AppDataBase::class.java,
                        "database.dp"
                    ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
                }

                INSTANCE = instance
                return INSTANCE!!
            }
        }


    }

}