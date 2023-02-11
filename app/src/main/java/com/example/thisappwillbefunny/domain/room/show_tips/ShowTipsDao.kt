package com.example.thisappwillbefunny.domain.room.show_tips

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ShowTipsDao {

    @Query("SELECT * FROM tip")
    fun getTip(): List<ShowTips>

    @Insert
    fun insertTip(tip: ShowTips)

}