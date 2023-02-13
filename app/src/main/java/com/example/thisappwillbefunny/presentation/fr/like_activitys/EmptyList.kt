package com.example.thisappwillbefunny.presentation.fr.like_activitys

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.thisappwillbefunny.R
import com.example.thisappwillbefunny.presentation.navigation.CHOOSE_ACTIVITY_ROUTE
import com.example.thisappwillbefunny.presentation.navigation.LIKE_ACTIVITY_ROUTE
import com.example.thisappwillbefunny.presentation.ui.elements.text.LargeText
import com.example.thisappwillbefunny.utils.UiConst
import com.example.thisappwillbefunny.utils.swipeRightToReturn
import kotlinx.coroutines.delay


@Composable fun EmptyListFragment(navController: NavController) {
    var isShowAnim by remember { mutableStateOf(false) }

    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.not_found))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = Int.MAX_VALUE
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(UiConst.Brushes.background)
            .swipeRightToReturn {
                navController.navigate(
                    CHOOSE_ACTIVITY_ROUTE,
                ) {
                    popUpTo(LIKE_ACTIVITY_ROUTE) {
                        inclusive = true
                    }
                }
            },
        contentAlignment = Alignment.Center,
    ){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(
                visible = isShowAnim,
                enter = slideInVertically(tween(300)) + fadeIn(tween(300)),
                exit = slideOutVertically(tween(300)) + fadeOut(tween(300))
            ) {
                LottieAnimation(
                    composition = composition,
                    progress = progress,
                    alignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth(.7f).height(UiConst.Size.heightLottieAnimTip),
                    contentScale = ContentScale.FillHeight
                )
            }

            AnimatedVisibility(
                visible = isShowAnim,
                enter = slideInVertically(tween(600)) { it / 2 } + fadeIn(tween(600)),
                exit = slideOutVertically(tween(300)) { it / 2 } + fadeOut(tween(300))
            ) {
                LargeText(
                    value = "You didn`t add any Activity. Please, add activity before enter here.",
                    fontFamily = null,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

    LaunchedEffect(key1 = isShowAnim, block = {
        delay(300)
        isShowAnim = true
    })
}