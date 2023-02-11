package com.example.thisappwillbefunny.presentation

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.thisappwillbefunny.presentation.navigation.AppNavController
import com.example.thisappwillbefunny.presentation.ui.theme.ThisAppWillBeFunnyTheme

class MainActivity : ComponentActivity() {

    private var isShowTips = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            ThisAppWillBeFunnyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val rememberNavController = rememberNavController()

                    AppNavController(navController = rememberNavController, isShowTips = isShowTips)
                }
            }
        }
    }

}

