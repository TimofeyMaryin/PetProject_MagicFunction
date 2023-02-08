package com.example.thisappwillbefunny.presentation.fr.select_internet_status

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.airbnb.lottie.compose.*
import com.example.thisappwillbefunny.R
import com.example.thisappwillbefunny.domain.model.TipInternetStatusModel
import com.example.thisappwillbefunny.presentation.ui.elements.text.MediumText
import com.example.thisappwillbefunny.presentation.ui.elements.text.SmallText
import com.example.thisappwillbefunny.presentation.ui.font.Raleway
import com.example.thisappwillbefunny.utils.UiConst

@Composable
fun TopBarInternetStatusFragment(
    value: TipInternetStatusModel,
    modifier: Modifier
) {
    var visibility by remember { mutableStateOf(false) }

    val isLoad: LottieCompositionResult = rememberLottieComposition(spec = LottieCompositionSpec.RawRes(
        R.raw.tips_1))
    val progress by animateLottieCompositionAsState(
        composition = isLoad.value,
        iterations = Int.MAX_VALUE
    )


    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .height(UiConst.Size.TOP_BAR_HEIGHT)
            .then(modifier)
    ) {
        val (videoPlace, tipsPlace, descTipsPlace, bottomLines) = createRefs()

        AnimatedVisibility(
            visible = visibility,
            enter = slideInHorizontally() + fadeIn(tween(300)),
            modifier = Modifier
                .size(UiConst.Size.TIPS_VIDEOS)
                .constrainAs(videoPlace) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        ) {
            LottieAnimation(
                composition = isLoad.value,
                progress = progress,
                alignment = Alignment.TopStart,
                modifier = Modifier.size(UiConst.Size.TIPS_VIDEOS)
            )
        }


        AnimatedVisibility(
            visible = visibility,
            enter = slideInVertically() + fadeIn(tween(300)),
            modifier = Modifier.constrainAs(descTipsPlace) {
                top.linkTo(videoPlace.top)
                start.linkTo(videoPlace.end)
                bottom.linkTo(videoPlace.bottom)
            }
        ) {
            Box(
                modifier = Modifier
                    .clip(UiConst.Round.NORMAL)
                    .background(Color.Red.copy(.8f))
                    .width(UiConst.Size.WIDTH_TIP_EL),
                contentAlignment = Alignment.Center
            ) {
                MediumText(
                    value = value.tips,
                    fontFamily = Raleway,
                    modifier = Modifier.padding(UiConst.Padding.SMALL),
                    fontWeight = FontWeight.Bold
                )
            }
        }


        AnimatedVisibility(
            visible = visibility,
            enter = slideInVertically { it / 2 } + fadeIn(tween(300)),
            modifier = Modifier.constrainAs(tipsPlace) {
                top.linkTo(descTipsPlace.bottom)
                end.linkTo(descTipsPlace.end)
            }
        ) {
            SmallText(value = value.tipsNumber)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth(.95f)
                .height(1.dp)
                .background(Color.White.copy(.4f))
                .constrainAs(bottomLines) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
        )
    }



    LaunchedEffect(
        key1 = isLoad.isComplete,
        key2 = Unit,
        block = {
            if (isLoad.isComplete) {
                visibility = true
            }
        }
    )
}
