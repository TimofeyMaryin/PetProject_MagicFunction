package com.example.thisappwillbefunny.presentation.fr.select_fun

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController

@Suppress("UNCHECKED_CAST")
class ChooseActivityViewModelFactory(
    private val navController: NavController
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChooseActivityViewModel(navController = navController) as T
    }
}