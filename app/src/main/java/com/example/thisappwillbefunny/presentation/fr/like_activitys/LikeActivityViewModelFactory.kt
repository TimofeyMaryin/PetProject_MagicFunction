package com.example.thisappwillbefunny.presentation.fr.like_activitys

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.thisappwillbefunny.domain.repository.LikeActivityRepository

@Suppress("UNCHECKED_CAST")
class LikeActivityViewModelFactory(
    private val repo: LikeActivityRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LikeActivitiesViewModel(repo = repo) as T
    }

}