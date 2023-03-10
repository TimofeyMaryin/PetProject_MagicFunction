package com.example.thisappwillbefunny.presentation.fr.select_activity

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.thisappwillbefunny.domain.repository.LikeActivityRepository

@Suppress("UNCHECKED_CAST")
class SelectActivityViewModelFactory(
    private val application: Application,
    private val repo: LikeActivityRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SelectActivityViewModel(
            application = application,
            repo = repo
        ) as T
    }
}