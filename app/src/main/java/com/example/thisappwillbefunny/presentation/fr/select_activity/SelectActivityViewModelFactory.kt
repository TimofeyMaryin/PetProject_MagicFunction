package com.example.thisappwillbefunny.presentation.fr.select_activity

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class SelectActivityViewModelFactory(
    private val application: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SelectActivityViewModel(application = application) as T
    }
}