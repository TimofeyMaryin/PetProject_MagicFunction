package com.example.thisappwillbefunny.presentation.fr.select_activity

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.thisappwillbefunny.R
import com.example.thisappwillbefunny.domain.model.RequestActivityModel
import com.example.thisappwillbefunny.domain.use_cases.GetCurrentActivity
import com.example.thisappwillbefunny.presentation.repository.ActivityInfoRepoImpl
import kotlinx.coroutines.coroutineScope

class SelectActivityViewModel : ViewModel() {

    var defaultCountRequest by mutableStateOf(10)
    fun getCurrentRawRes(type: String): Int =
        when(type){
            "education" -> R.raw.reading_boy
            "recreational" -> R.raw.relaxation
            "social" -> R.raw.social
            "diy" -> R.raw.diy
            "charity" -> R.raw.charity
            "cooking" -> R.raw.cooking
            "relaxation" -> R.raw.relax
            "music" -> R.raw.music
            "busywork" -> R.raw.busywork
            else -> -1
        }

    suspend fun requestActivity(): MutableList<RequestActivityModel> {
        Log.e("requestActivity", "function start", )
        val result = mutableListOf<RequestActivityModel>()
        coroutineScope {
            Log.e("requestActivity", "coroutines is start", )
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
                Log.e("requestActivity", "add el", )
            }

        }
        Log.e("requestActivity", "coroutines is finished like and function", )

        return result
    }

    private val activityRepo = ActivityInfoRepoImpl()
}