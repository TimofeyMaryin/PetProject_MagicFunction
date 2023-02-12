package com.example.thisappwillbefunny.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.thisappwillbefunny.presentation.fr.get_random_cat.GetRandomCatFragment
import com.example.thisappwillbefunny.presentation.fr.get_random_cat.GetRandomCatViewModel
import com.example.thisappwillbefunny.presentation.fr.get_random_cat.GetRandomCatViewModelFactory
import com.example.thisappwillbefunny.presentation.fr.lost_internet_connection.LostInternetConnection
import com.example.thisappwillbefunny.presentation.fr.select_activity.SelectActivityFragment
import com.example.thisappwillbefunny.presentation.fr.select_activity.SelectActivityViewModel
import com.example.thisappwillbefunny.presentation.fr.select_fun.ChooseActivityFragment
import com.example.thisappwillbefunny.presentation.fr.select_fun.ChooseActivityViewModel
import com.example.thisappwillbefunny.presentation.fr.select_fun.ChooseActivityViewModelFactory
import com.example.thisappwillbefunny.presentation.fr.select_internet_status.SelectInternetStatusFragment
import com.example.thisappwillbefunny.presentation.fr.select_internet_status.SelectInternetStatusViewModel
import com.example.thisappwillbefunny.presentation.fr.select_internet_status.SelectInternetStatusViewModelFactory
import com.example.thisappwillbefunny.utils.isOnline

@Composable
fun AppNavController(
    navController: NavHostController
) {
    val context = LocalContext.current
    val chooseActivityViewModel: ChooseActivityViewModel = viewModel(factory = ChooseActivityViewModelFactory(navController = navController))
    val getRandomCatViewModel: GetRandomCatViewModel = viewModel(factory = GetRandomCatViewModelFactory(navController = navController))
    val selectInternetStatusViewModel: SelectInternetStatusViewModel = viewModel(factory = SelectInternetStatusViewModelFactory(navController = navController))
    val selectActivityViewModel: SelectActivityViewModel = viewModel()


    NavHost(navController = navController, startDestination = CHOOSE_ACTIVITY_ROUTE ) {
        composable(CHOOSE_ACTIVITY_ROUTE) {
            ChooseActivityFragment(viewModel = chooseActivityViewModel)
        }
        composable(GET_RANDOM_CAT_ROUTE) {
            checkIsOnline(isOnlineUser = { GetRandomCatFragment(viewModel = getRandomCatViewModel, navController = navController) }, context = context)
        }
        composable(SELECT_INTERNET_STATUS_ROUTE) {
            checkIsOnline(isOnlineUser = { SelectInternetStatusFragment(viewModel = selectInternetStatusViewModel, navController = navController) }, context = context)

        }
        composable(SELECT_ACTIVITY_ROUTE) {
            checkIsOnline(isOnlineUser = { SelectActivityFragment(viewModel = selectActivityViewModel, navController = navController) }, context = context)

        }

    }
}

@Composable private fun checkIsOnline(isOnlineUser: @Composable () -> Unit, context: Context){
    if (isOnline(context = context)) {
        isOnlineUser()
    } else {
        LostInternetConnection()
    }
}

/*
if (isOnline(context = context)) {
    SelectActivityFragment(
        viewModel = selectActivityViewModel,
        navController = navController
    )
} else {
    LostInternetConnection()
}*/
