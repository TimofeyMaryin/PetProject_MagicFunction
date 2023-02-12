package com.example.thisappwillbefunny.presentation


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.thisappwillbefunny.domain.repository.LikeActivityRepository
import com.example.thisappwillbefunny.presentation.navigation.AppNavController
import com.example.thisappwillbefunny.presentation.ui.theme.ThisAppWillBeFunnyTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val likeActivityRepository = LikeActivityRepository(application = application)

        setContent {
            ThisAppWillBeFunnyTheme {
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
    }


}

