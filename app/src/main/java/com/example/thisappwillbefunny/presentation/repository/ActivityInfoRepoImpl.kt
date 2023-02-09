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
            0.0f -> { ActivityItemDescModel(bg = status.easy.first, icon = R.drawable.accessibility_easy, iconColor = status.easy.second, R.string.accessibility_easy, "Accessibility") }
            in 0.0001f..0.7f -> { ActivityItemDescModel(bg = status.normal.first, icon = R.drawable.accessibility_normal, iconColor = status.normal.second, R.string.accessibility_normal, "Accessibility") }
            in 0.7001f..1f -> { ActivityItemDescModel(bg = status.hard.first, icon = R.drawable.accessibility_hard, iconColor = status.hard.second, R.string.accessibility_hard, "Accessibility") }
            else -> { ActivityItemDescModel(bg = status.normal.first, icon = R.drawable.ic_launcher_foreground, iconColor = status.normal.second, -1, "Accessibility") }
        }
    }

    override fun getParticipants(value: Int): ActivityItemDescModel {
        val status = UiConst.Brushes.ActivityTipsStatus
        return when (value) {
            in 1..3 -> { ActivityItemDescModel(bg = status.easy.first, icon = R.drawable.particants_easy, iconColor = status.easy.second, R.string.participants_easy, "Participants") }
            in 4..10 -> { ActivityItemDescModel(bg = status.normal.first, icon = R.drawable.particanst_normal, iconColor = status.normal.second, R.string.participants_normal, "Participants") }
            else -> { ActivityItemDescModel(bg = status.hard.first, icon = R.drawable.particant_hard, iconColor = status.hard.second, R.string.participants_hard, "Participants") }
        }
    }

    override fun getPrice(value: Float): ActivityItemDescModel {
        val status = UiConst.Brushes.ActivityTipsStatus
        return when(value) {
            0.0f -> { ActivityItemDescModel(bg = status.easy.first, icon = R.drawable.price_ez, iconColor = status.easy.second, R.string.price_easy, "Price") }
            in 0.0001f..0.7f -> { ActivityItemDescModel(bg = status.normal.first, icon = R.drawable.price_normal, iconColor = status.normal.second, R.string.price_normal, "Price") }
            in 0.7001f..1f -> { ActivityItemDescModel(bg = status.hard.first, icon = R.drawable.price_hard, iconColor = status.hard.second, R.string.price_hard, "Price") }
            else -> { ActivityItemDescModel(bg = status.normal.first, icon = R.drawable.ic_launcher_foreground, iconColor = status.normal.second, -1, "Price Error") }
        }
    }

}