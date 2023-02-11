package com.example.thisappwillbefunny.domain.room

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
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


    companion object {
        @Volatile var INSTANCE: AppDataBase? = null

        fun getInstance(application: Application): AppDataBase {
            var instance = INSTANCE

            synchronized(this) {
                if (instance == null){
                    instance = Room.databaseBuilder(
                        application,
                        AppDataBase::class.java,
                        "tips"
                    ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
                }
            }

            INSTANCE = instance
            return INSTANCE!!
        }
    }
    
}