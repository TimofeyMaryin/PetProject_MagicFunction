package com.example.thisappwillbefunny.presentation.fr.select_internet_status

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController

@Suppress("UNCHECKED_CAST")
class SelectInternetStatusViewModelFactory(
    private val navController: NavController
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SelectInternetStatusViewModel(navController = navController) as T
    }

}