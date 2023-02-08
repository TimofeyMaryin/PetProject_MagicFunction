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
        val status = UiConst.Brushes.ActivityTipsStatus
        return when(value) {
            0.0f -> { ActivityItemDescModel(bg = status.easy.first, icon = R.drawable.back, iconColor = status.easy.second, "Accessibility Easy", "Accessibility") }
            in 0.0001f..0.7f -> { ActivityItemDescModel(bg = status.normal.first, icon = R.drawable.ic_launcher_foreground, iconColor = status.normal.second, "Accessibility Normal", "Accessibility") }
            in 0.7001f..1f -> { ActivityItemDescModel(bg = status.hard.first, icon = R.drawable.reload, iconColor = status.hard.second, "Accessibility hard", "Accessibility") }
            else -> { ActivityItemDescModel(bg = status.normal.first, icon = R.drawable.ic_launcher_foreground, iconColor = status.normal.second, "", "Accessibility") }
        }
    }

    override fun getParticipants(value: Int): ActivityItemDescModel {
        val status = UiConst.Brushes.ActivityTipsStatus
        return when (value) {
            in 1..3 -> { ActivityItemDescModel(bg = status.easy.first, icon = R.drawable.back, iconColor = status.easy.second, "Participants Easy", "Participants") }
            in 4..10 -> { ActivityItemDescModel(bg = status.normal.first, icon = R.drawable.back, iconColor = status.normal.second, "Participants Normal", "Participants") }
            else -> { ActivityItemDescModel(bg = status.hard.first, icon = R.drawable.back, iconColor = status.hard.second, "Participants Hard", "Participants") }
        }
    }

    override fun getPrice(value: Float): ActivityItemDescModel {
        val status = UiConst.Brushes.ActivityTipsStatus
        return when(value) {
            0.0f -> { ActivityItemDescModel(bg = status.easy.first, icon = R.drawable.back, iconColor = status.easy.second, "Price Easy", "Price") }
            in 0.0001f..0.7f -> { ActivityItemDescModel(bg = status.normal.first, icon = R.drawable.ic_launcher_foreground, iconColor = status.normal.second, "Price Normal", "Price") }
            in 0.7001f..1f -> { ActivityItemDescModel(bg = status.hard.first, icon = R.drawable.reload, iconColor = status.hard.second, "Price Hard", "Price") }
            else -> { ActivityItemDescModel(bg = status.normal.first, icon = R.drawable.ic_launcher_foreground, iconColor = status.normal.second, "Price Error", "Price Error") }
        }
    }

}