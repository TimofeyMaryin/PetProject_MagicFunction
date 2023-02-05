package com.example.thisappwillbefunny.presentation.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color


data class FeatureElementModel(
    val title: String,
    val background: Brush,
    val textColor: Color
)
