package com.example.thisappwillbefunny.presentation.fr.select_fun

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class ChooseActivityViewModel(
    private val navController: NavController
): ViewModel() {

    fun navigate(route: String) {
        navController.navigate(route = route)
    }

}