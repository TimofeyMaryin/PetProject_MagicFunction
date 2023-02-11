package com.example.thisappwillbefunny.domain.room.show_tips

import androidx.room.Entity


@Entity(tableName = "tip")
data class ShowTips(
    var isShow: Boolean
)
