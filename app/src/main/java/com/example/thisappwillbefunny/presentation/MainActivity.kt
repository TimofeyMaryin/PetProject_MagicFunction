package com.example.thisappwillbefunny.presentation


import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.example.thisappwillbefunny.domain.repository.LikeActivityRepository
import com.example.thisappwillbefunny.presentation.navigation.AppNavController
import com.example.thisappwillbefunny.presentation.ui.theme.ThisAppWillBeFunnyTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val likeActivityRepository = LikeActivityRepository(application = application)

        setContent {
            ThisAppWillBeFunnyTheme {

                val systemUiController = rememberSystemUiController()
                SideEffect {
                    // set transparent color so that our image is visible
                    // behind the status bar
                    systemUiController.setStatusBarColor(color = Color.Blue)
                }


                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val rememberNavController = rememberNavController()


                    AppNavController(
                        navController = rememberNavController,
                        application = application,
                        likeActivityRepository = likeActivityRepository
                    )
                }
            }
        }
        hideSystemUI()
    }


    private fun hideSystemUI() {

        //Hides the ugly action bar at the top
        actionBar?.hide()

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        } else {
            window.insetsController?.apply {
                hide(WindowInsets.Type.statusBars())
                systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
    }
}

