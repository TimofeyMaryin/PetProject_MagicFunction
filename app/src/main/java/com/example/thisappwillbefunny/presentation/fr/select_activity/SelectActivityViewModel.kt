package com.example.thisappwillbefunny.presentation.fr.select_activity

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.thisappwillbefunny.R
import com.example.thisappwillbefunny.domain.model.ActivityItemDescModel
import com.example.thisappwillbefunny.domain.model.FilterElementModel
import com.example.thisappwillbefunny.domain.model.RequestActivityModel
import com.example.thisappwillbefunny.domain.use_cases.GetCurrentActivity
import com.example.thisappwillbefunny.presentation.repository.ActivityInfoRepoImpl
import com.example.thisappwillbefunny.utils.UiConst
import com.example.thisappwillbefunny.utils.emptyRequestActivityModel
import kotlinx.coroutines.coroutineScope

class SelectActivityViewModel : ViewModel() {

    private var currentFilterIndex by mutableStateOf(0)

    var defaultCountRequest by mutableStateOf(10)
    var currentRequest by mutableStateOf(listOf(emptyRequestActivityModel))
    val activityRepo = ActivityInfoRepoImpl()

    val listOfFilterElement by mutableStateOf(
        listOf(

            FilterElementModel(R.drawable.reload, mutableStateOf(true)),
            FilterElementModel(R.drawable.accessibility_easy, mutableStateOf(false)),
            FilterElementModel(R.drawable.particants_easy, mutableStateOf(false)),
            FilterElementModel(R.drawable.price_ez, mutableStateOf(false)),
        )
    )

    fun selectFilterEl(index: Int) {
        listOfFilterElement[currentFilterIndex].isActive.value = false
        currentFilterIndex = index
        listOfFilterElement[currentFilterIndex].isActive.value = true
        Log.e("selectFilterEl", "currentFilterIndex: $currentFilterIndex", )

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




}