package com.example.thisappwillbefunny.presentation.fr.get_random_cat

import android.widget.ViewSwitcher.ViewFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController


@Suppress("UNCHECKED_CAST")
class GetRandomCatViewModelFactory(
    private val navController: NavController
): ViewModelProvider.Factory  {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GetRandomCatViewModel(navController = navController) as T
    }
}