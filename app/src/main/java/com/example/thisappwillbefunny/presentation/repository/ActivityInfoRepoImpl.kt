package com.example.thisappwillbefunny.presentation.repository

import androidx.compose.ui.graphics.Color
import androidx.core.util.rangeTo
import com.example.thisappwillbefunny.R
import com.example.thisappwillbefunny.domain.model.ActivityItemDescModel
import com.example.thisappwillbefunny.domain.model.ActivityModel
import com.example.thisappwillbefunny.domain.repository.ActivityInfoRepository
import com.example.thisappwillbefunny.utils.UiConst

class ActivityInfoRepoImpl: ActivityInfoRepository {
    override fun getAccessibility(value: Float): ActivityItemDescModel {
        return when(value) {
            in 0f..0.3f -> ActivityItemDescModel(UiConst.Brushes.BLUE_PINK, R.drawable.reload, Color.White, "Desc ".repeat(3), "Accessibility")
            in 0.30001f..0.5f -> ActivityItemDescModel(UiConst.Brushes.BLUE_PINK, R.drawable.reload, Color.White, "Desc ".repeat(4), "Accessibility")
            in 0.50005f..0.7f -> ActivityItemDescModel(UiConst.Brushes.BLUE_PINK, R.drawable.reload, Color.White, "Desc ".repeat(7), "Accessibility")
            in 0.70001f..1f -> ActivityItemDescModel(UiConst.Brushes.BLUE_PINK, R.drawable.reload,Color.White, "Desc ".repeat(3), "Accessibility")
            else -> ActivityItemDescModel(UiConst.Brushes.BLUE_PINK, R.drawable.reload,Color.White, "Desc ".repeat(3), "Accessibility")
        }
    }

    override fun getParticipants(value: Int): ActivityItemDescModel {
        return when(value) {
            in 1..3 -> ActivityItemDescModel(UiConst.Brushes.RAINBOW_BLUE, R.drawable.reload, Color.White, "Des part".repeat(4), "Participants")
            in 4..7 -> ActivityItemDescModel(UiConst.Brushes.RAINBOW_BLUE, R.drawable.reload, Color.White, "Des part".repeat(2), "Participants")
            in 8..12 -> ActivityItemDescModel(UiConst.Brushes.RAINBOW_BLUE, R.drawable.reload, Color.White, "Des part".repeat(6), "Participants")
            else -> ActivityItemDescModel(UiConst.Brushes.RAINBOW_BLUE, R.drawable.reload, Color.White, "Error", "Error")
        }
    }

    override fun getPrice(value: Float): ActivityItemDescModel {
        return when(value) {
            0f -> ActivityItemDescModel(UiConst.Brushes.MIAKA, R.drawable.reload, Color.White, "Desc price ".repeat(2), "Price")
            in 0.0001f..0.3f -> ActivityItemDescModel(UiConst.Brushes.MIAKA, R.drawable.reload, Color.White, "Desc price ".repeat(5), "Price")
            in 0.3001f..0.5f -> ActivityItemDescModel(UiConst.Brushes.MIAKA, R.drawable.reload, Color.White, "Desc price ".repeat(2), "Price")
            in 0.5001f..0.7f -> ActivityItemDescModel(UiConst.Brushes.MIAKA, R.drawable.reload, Color.White, "Desc price ".repeat(1), "Price")
            in 0.7001f..1f -> ActivityItemDescModel(UiConst.Brushes.MIAKA, R.drawable.reload, Color.White, "Desc price ".repeat(4), "Price")
            else -> ActivityItemDescModel(UiConst.Brushes.MIAKA, R.drawable.reload, Color.White, "Error", "Error")
        }
    }

}