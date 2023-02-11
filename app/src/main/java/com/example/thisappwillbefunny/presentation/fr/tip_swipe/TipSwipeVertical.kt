package com.example.thisappwillbefunny.presentation.fr.tip_swipe

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
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
import kotlinx.coroutines.delay

@Composable
fun TipSwipeVertical(swipe: () -> Unit) {
    val animateSpec: LottieCompositionResult = rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.swipe_up))
    var isSwipe by remember { mutableStateOf(false) }
    var showAnim by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .designTip()
            .pointerInput(Unit) {
                detectDragGestures { _, dragAmount ->
                    val (x, y) = dragAmount

                    when {
                        y < 200 -> {
                            isSwipe = !isSwipe
                            showAnim = !showAnim
                        }
                    }
                }
            },
        contentAlignment = Alignment.Center,
    ) {
        AnimatedVisibility(
            visible = showAnim,
            enter = fadeIn(tween(1000)),
            exit = fadeOut(tween(1000))
        ) {
            VerticalSwipeContent(animateSpec)
        }
    }


    LaunchedEffect(
        key1 = isSwipe,
        block = {

            if (isSwipe){
                delay(400)
                swipe()
            }
        },
    )
}


@Composable
private fun VerticalSwipeContent(spec: LottieCompositionResult) {
    val progress by animateLottieCompositionAsState(
        composition = spec.value,
        iterations = Int.MAX_VALUE
    )


    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnimation(
            composition = spec.value,
            progress = progress,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )

        LargeText(
            value = "Swipe vertical to return",
            fontFamily = Raleway,
            fontWeight = FontWeight.Bold
        )
    }
}