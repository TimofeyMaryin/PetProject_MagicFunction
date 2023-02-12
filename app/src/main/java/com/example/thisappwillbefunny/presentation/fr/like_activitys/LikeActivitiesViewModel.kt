package com.example.thisappwillbefunny.presentation.fr.like_activitys

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.thisappwillbefunny.domain.repository.LikeActivityRepository
import com.example.thisappwillbefunny.domain.room.like_activities.LikeActivitiesEntity
import kotlinx.coroutines.coroutineScope

class LikeActivitiesViewModel(
    private val repo: LikeActivityRepository
): ViewModel() {

    suspend fun getCountLikedActivity(): Int {

        return  repo.getLikedActivities().size
    }

    fun getElement(index: Int): LikeActivitiesEntity {
        return repo.getLikedActivities()[index]
    }


}