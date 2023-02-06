package com.example.thisappwillbefunny.presentation.fr.lost_internet_connection

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.*
import com.example.thisappwillbefunny.R
import com.example.thisappwillbefunny.presentation.ui.elements.text.LargeText
import com.example.thisappwillbefunny.presentation.ui.font.Raleway

@Composable
fun LostInternetConnection() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Item()
    }
}


@Composable
private fun Item() {
    var visible by remember { mutableStateOf(false) }
    val spec: LottieCompositionResult = rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.no_internet_connection))
    val progress by animateLottieCompositionAsState(
        composition = spec.value,
        iterations = Int.MAX_VALUE
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically() + fadeIn(tween(300))
        ) {
            LottieAnimation(
                composition = spec.value,
                progress = progress,
            )
        }

        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically { it / 2 } + fadeIn(tween(300))
        ) {
            LargeText(
                value = "NO INTERNET CONNECTION",
                fontFamily = Raleway,
                color = MaterialTheme.colors.error
            )
        }
    }

    LaunchedEffect(key1 = spec.isComplete, block = {
        if (spec.isComplete) {
            visible = true
        }
    })
}