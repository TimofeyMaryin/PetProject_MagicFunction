package com.example.thisappwillbefunny.domain.model

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

data class ActivityItemDescModel(
    val bg: Brush,
    val icon: Int,
    val iconColor: Color,
    val desc: Int,
    val nameTypeActivity: String
)
