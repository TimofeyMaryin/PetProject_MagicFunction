package com.example.thisappwillbefunny.presentation.fr.select_activity

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.thisappwillbefunny.R
import com.example.thisappwillbefunny.domain.model.ActivityItemDescModel
import com.example.thisappwillbefunny.domain.model.RequestActivityModel
import com.example.thisappwillbefunny.domain.use_cases.GetCurrentActivity
import com.example.thisappwillbefunny.presentation.repository.ActivityInfoRepoImpl
import com.example.thisappwillbefunny.utils.UiConst
import com.example.thisappwillbefunny.utils.emptyRequestActivityModel
import kotlinx.coroutines.coroutineScope

class SelectActivityViewModel : ViewModel() {

    var defaultCountRequest by mutableStateOf(10)
    var currentRequest by mutableStateOf(
        listOf(emptyRequestActivityModel)
    )

    fun setAccessibility(value: Float): ActivityItemDescModel {
        val status = UiConst.Brushes.ActivityTipsStatus
        return when(value) {
            0.0f -> { ActivityItemDescModel(bg = status.easy.first, icon = R.drawable.back, iconColor = status.easy.second, "", "") }
            in 0.0001f..0.7f -> { ActivityItemDescModel(bg = status.normal.first, icon = R.drawable.ic_launcher_foreground, iconColor = status.normal.second, "", "") }
            in 0.7001f..1f -> { ActivityItemDescModel(bg = status.hard.first, icon = R.drawable.reload, iconColor = status.hard.second, "", "") }
            else -> { ActivityItemDescModel(bg = status.normal.first, icon = R.drawable.ic_launcher_foreground, iconColor = status.normal.second, "", "") }
        }
    }

    fun setParticipants(value: Int): ActivityItemDescModel {
        val status = UiConst.Brushes.ActivityTipsStatus
        return when (value) {
            in 1..3 -> { ActivityItemDescModel(bg = status.easy.first, icon = R.drawable.back, iconColor = status.easy.second, "", "") }
            in 4..10 -> { ActivityItemDescModel(bg = status.normal.first, icon = R.drawable.back, iconColor = status.normal.second, "", "") }
            else -> { ActivityItemDescModel(bg = status.hard.first, icon = R.drawable.back, iconColor = status.hard.second, "", "") }
        }
    }

    fun setPrice(value: Float): ActivityItemDescModel{
        val status = UiConst.Brushes.ActivityTipsStatus
        return when(value) {
            0.0f -> { ActivityItemDescModel(bg = status.easy.first, icon = R.drawable.back, iconColor = status.easy.second, "", "") }
            in 0.0001f..0.7f -> { ActivityItemDescModel(bg = status.normal.first, icon = R.drawable.ic_launcher_foreground, iconColor = status.normal.second, "", "") }
            in 0.7001f..1f -> { ActivityItemDescModel(bg = status.hard.first, icon = R.drawable.reload, iconColor = status.hard.second, "", "") }
            else -> { ActivityItemDescModel(bg = status.normal.first, icon = R.drawable.ic_launcher_foreground, iconColor = status.normal.second, "", "") }
        }
    }

    suspend fun requestActivity(): MutableList<RequestActivityModel> {
        val result = mutableListOf<RequestActivityModel>()
        coroutineScope {
            for (i in 0 until defaultCountRequest) {
                val currentRequest = GetCurrentActivity.execute()
                result.add(
                    RequestActivityModel(
                        activityPOJO = currentRequest,
                        accessibleModel = activityRepo.getAccessibility(currentRequest.accessibility.toFloat()),
                        participants = activityRepo.getParticipants(currentRequest.participants),
                        pricing = activityRepo.getPrice(currentRequest.price.toFloat())
                    )
                )
            }

        }

        return result
    }

    private val activityRepo = ActivityInfoRepoImpl()




}