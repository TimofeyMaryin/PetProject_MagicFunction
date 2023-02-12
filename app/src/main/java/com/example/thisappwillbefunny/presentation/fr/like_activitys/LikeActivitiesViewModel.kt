package com.example.thisappwillbefunny.presentation.fr.like_activitys

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.thisappwillbefunny.domain.repository.LikeActivityRepository

class LikeActivitiesViewModel(
    private val repo: LikeActivityRepository
): ViewModel() {
    var count by mutableStateOf(-1)
    suspend fun getCountLikedActivity() {
        count = repo.getLikedActivities().size
    }

}