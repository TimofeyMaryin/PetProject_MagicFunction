package com.example.thisappwillbefunny.presentation.fr.get_random_cat

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.thisappwillbefunny.domain.use_cases.GetRandomCats
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.URL

class GetRandomCatViewModel(
    private val navController: NavController
): ViewModel() {

    suspend fun getRandomCat(): String = GetRandomCats().execute()
    fun popBackStack() = navController.popBackStack()


}