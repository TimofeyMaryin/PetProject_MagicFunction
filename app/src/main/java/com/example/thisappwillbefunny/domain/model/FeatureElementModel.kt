package com.example.thisappwillbefunny.domain.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color


data class FeatureElementModel(
    val title: String,
    val background: Brush,
    val textColor: Color,
    val icon: Int
)
