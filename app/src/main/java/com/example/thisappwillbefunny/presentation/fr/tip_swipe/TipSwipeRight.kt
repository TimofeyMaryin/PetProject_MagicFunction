package com.example.thisappwillbefunny.presentation.fr.tip_swipe

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import com.airbnb.lottie.compose.*
import com.example.thisappwillbefunny.R
import com.example.thisappwillbefunny.presentation.ui.elements.text.LargeText
import com.example.thisappwillbefunny.presentation.ui.font.Raleway

@Composable
fun TipSwipeRight(
    swipeRight: () -> Unit
) {

    val animateSpec: LottieCompositionResult = rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.swipe_right))

    Box(
        modifier = Modifier
            .designTip()
            .pointerInput(Unit) {
                detectDragGestures { _, dragAmount ->
                    val (x, y) = dragAmount

                    when {
                        x > 200 -> {
                            animateSpec.error
                            swipeRight()
                        }
                    }
                }
            },
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = animateSpec.isComplete,
            enter = fadeIn(tween(1000)),
            exit = fadeOut(tween(1000))
        ) {
            TipSwipeRightContent(animateSpec)
        }
    }



}

@Composable
private fun TipSwipeRightContent(spec: LottieCompositionResult) {

    val progress by animateLottieCompositionAsState(
        composition = spec.value,
        iterations = Int.MAX_VALUE
    )
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = spec.value,
            progress = progress,
            alignment = Alignment.Center,
            contentScale = ContentScale.FillWidth
        )

        LargeText(
            value = "Swipe Right to return",
            fontFamily = Raleway,
            fontWeight = FontWeight.Bold,
        )

    }
}