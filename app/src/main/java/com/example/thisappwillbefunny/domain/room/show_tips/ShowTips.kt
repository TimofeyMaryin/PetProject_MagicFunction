package com.example.thisappwillbefunny.domain.room.show_tips

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tip")
data class ShowTips(
    @PrimaryKey var id: Int = 0,
    @ColumnInfo(name = "isShow") var isShow: Boolean
)
