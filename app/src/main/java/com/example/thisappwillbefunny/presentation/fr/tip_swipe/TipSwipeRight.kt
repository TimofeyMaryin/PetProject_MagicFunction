package com.example.thisappwillbefunny.presentation.fr.tip_swipe

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import com.airbnb.lottie.compose.*
import com.example.thisappwillbefunny.R
import com.example.thisappwillbefunny.presentation.ui.elements.text.LargeText
import com.example.thisappwillbefunny.presentation.ui.font.Raleway
import com.example.thisappwillbefunny.utils.designTip
import com.example.thisappwillbefunny.utils.swipeRightToReturn

@Composable
fun TipSwipeRight(
    modifier: Modifier = Modifier,
    swipeRight: () -> Unit
) {

    val animateSpec: LottieCompositionResult = rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.swipe_right))

    Box(
        modifier = Modifier
            .designTip()
            .swipeRightToReturn {
                animateSpec.error
                swipeRight()
            }
            .then(modifier),
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