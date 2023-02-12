package com.example.thisappwillbefunny.presentation.fr.get_random_cat

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.Looper
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.thisappwillbefunny.domain.use_cases.DownloadImage
import com.example.thisappwillbefunny.domain.use_cases.GetRandomCats
import okio.IOException
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.concurrent.Executors

class GetRandomCatViewModel(
    private val navController: NavController
): ViewModel() {

    suspend fun getRandomCat(): String = GetRandomCats().execute()
    fun popBackStack() = navController.popBackStack()

    fun downloadImage(url: String, context: Context) = DownloadImage.execute(url = url, context = context)


}