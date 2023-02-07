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
            in 0f..0.3f -> ActivityItemDescModel(UiConst.Brushes.BLUE_PINK, R.drawable.reload, Color.White, "Accessibility")
            in 0.30001f..0.5f -> ActivityItemDescModel(UiConst.Brushes.BLUE_PINK, R.drawable.reload, Color.White, "Accessibility")
            in 0.50005f..0.7f -> ActivityItemDescModel(UiConst.Brushes.BLUE_PINK, R.drawable.reload, Color.White, "Accessibility")
            in 0.70001f..1f -> ActivityItemDescModel(UiConst.Brushes.BLUE_PINK, R.drawable.reload,Color.White, "Accessibility")
            else -> ActivityItemDescModel(UiConst.Brushes.BLUE_PINK, R.drawable.reload,Color.White, "Error")
        }
    }

    override fun getParticipants(value: Int): ActivityItemDescModel {
        return when(value) {
            in 1..3 -> ActivityItemDescModel(UiConst.Brushes.RAINBOW_BLUE, R.drawable.reload, Color.White, "Participants")
            in 4..7 -> ActivityItemDescModel(UiConst.Brushes.RAINBOW_BLUE, R.drawable.reload, Color.White, "Participants")
            in 8..12 -> ActivityItemDescModel(UiConst.Brushes.RAINBOW_BLUE, R.drawable.reload, Color.White, "Participants")
            else -> ActivityItemDescModel(UiConst.Brushes.RAINBOW_BLUE, R.drawable.reload, Color.White, "Error")
        }
    }

    override fun getPrice(value: Float): ActivityItemDescModel {
        return when(value) {
            0f -> ActivityItemDescModel(UiConst.Brushes.MIAKA, R.drawable.reload, Color.White, "Price")
            in 0.0001f..0.3f -> ActivityItemDescModel(UiConst.Brushes.MIAKA, R.drawable.reload, Color.White, "Price")
            in 0.3001f..0.5f -> ActivityItemDescModel(UiConst.Brushes.MIAKA, R.drawable.reload, Color.White, "Price")
            in 0.5001f..0.7f -> ActivityItemDescModel(UiConst.Brushes.MIAKA, R.drawable.reload, Color.White, "Price")
            in 0.7001f..1f -> ActivityItemDescModel(UiConst.Brushes.MIAKA, R.drawable.reload, Color.White, "Price")
            else -> ActivityItemDescModel(UiConst.Brushes.MIAKA, R.drawable.reload, Color.White, "Error")
        }
    }

}