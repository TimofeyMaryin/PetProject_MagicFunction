package com.example.thisappwillbefunny.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.thisappwillbefunny.presentation.fr.get_random_cat.GetRandomCatFragment
import com.example.thisappwillbefunny.presentation.fr.get_random_cat.GetRandomCatViewModel
import com.example.thisappwillbefunny.presentation.fr.get_random_cat.GetRandomCatViewModelFactory
import com.example.thisappwillbefunny.presentation.fr.select_activity.SelectActivityFragment
import com.example.thisappwillbefunny.presentation.fr.select_fun.ChooseActivityFragment
import com.example.thisappwillbefunny.presentation.fr.select_fun.ChooseActivityViewModel
import com.example.thisappwillbefunny.presentation.fr.select_fun.ChooseActivityViewModelFactory
import com.example.thisappwillbefunny.presentation.fr.select_internet_status.SelectInternetStatusFragment

@Composable
fun AppNavController(navController: NavHostController) {
    val chooseActivityViewModel: ChooseActivityViewModel = viewModel(factory = ChooseActivityViewModelFactory(navController = navController))
    val getRandomCatViewModel: GetRandomCatViewModel = viewModel(factory = GetRandomCatViewModelFactory(navController = navController))

    NavHost(navController = navController, startDestination = CHOOSE_ACTIVITY_ROUTE ) {
        composable(CHOOSE_ACTIVITY_ROUTE) { ChooseActivityFragment(viewModel = chooseActivityViewModel) }
        composable(GET_RANDOM_CAT_ROUTE) { GetRandomCatFragment(viewModel = getRandomCatViewModel) }
        composable(SELECT_INTERNET_STATUS_ROUTE) { SelectInternetStatusFragment() }
        composable(SELECT_ACTIVITY_ROUTE) { SelectActivityFragment() }
    }
}