package com.example.thisappwillbefunny.presentation.fr.select_internet_status

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.thisappwillbefunny.domain.use_cases.DownloadImage

class SelectInternetStatusViewModel(
    private val navController: NavController
): ViewModel() {

    fun downloadImage(url: String, context: Context) = DownloadImage.execute(url = url, context = context)

}