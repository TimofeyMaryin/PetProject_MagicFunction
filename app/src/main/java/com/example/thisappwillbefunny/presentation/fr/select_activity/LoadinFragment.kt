package com.example.thisappwillbefunny.presentation.fr.select_activity

import android.widget.GridLayout.Spec
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.thisappwillbefunny.R
import com.example.thisappwillbefunny.presentation.ui.elements.text.LargeText
import com.example.thisappwillbefunny.utils.UiConst
import com.example.thisappwillbefunny.utils.createStartFragment


@Composable fun LoadingFragment(navController: NavController) {
    Box(
        modifier = Modifier.createStartFragment(navController = navController),
        contentAlignment = Alignment.Center
    ) {
        LoadingFragmentContent()
    }
}



@Composable private fun LoadingFragmentContent() {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = Int.MAX_VALUE
    )

    Column(
        modifier = Modifier
            .clip(UiConst.Round.NORMAL)
            .fillMaxWidth(.7f)
            .background(Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = composition,
            progress = progress,
            alignment = Alignment.Center,
            modifier = Modifier.height(UiConst.Size.WIDTH_TIP_EL)
        )
        LargeText(
            value = "Loading data",
            fontFamily = null,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(bottom = UiConst.Padding.MEDIUM),
            textAlign = TextAlign.Center
        )
    }
}