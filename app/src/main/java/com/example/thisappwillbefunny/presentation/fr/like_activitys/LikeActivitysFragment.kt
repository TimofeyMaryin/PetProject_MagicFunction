package com.example.thisappwillbefunny.presentation.fr.like_activitys

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController


@Composable fun LikeActivitiesFragment(
    navController: NavController,
    viewModel: LikeActivitiesViewModel
) {
    val count by remember { mutableStateOf(viewModel.count) }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Count likes post: $count")
    }


    LaunchedEffect(key1 = Unit, block = {
        viewModel.getCountLikedActivity()
    })
}