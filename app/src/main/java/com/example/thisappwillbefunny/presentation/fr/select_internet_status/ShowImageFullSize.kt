package com.example.thisappwillbefunny.presentation.fr.select_internet_status

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.example.thisappwillbefunny.presentation.fr.tip_swipe.TipSwipeVertical
import com.example.thisappwillbefunny.presentation.ui.elements.AppButton
import com.example.thisappwillbefunny.utils.UiConst
import kotlinx.coroutines.delay

@Composable
fun ShowImageFullSize(
    url: String,
    viewModel: SelectInternetStatusViewModel,
    onBack: () -> Unit,
) {
    var isShowTips by remember { mutableStateOf(false) }
    val context = LocalContext.current


    var visibleAnimation by remember { mutableStateOf(false) }
    var canCloseWin by remember { mutableStateOf(false) }

    ConstraintLayout(
        modifier = Modifier.fillMaxSize().pointerInput(Unit){
            detectDragGestures { _, dragAmount ->
                val (x,y) = dragAmount

                when {
                    y < 400 -> {
                        visibleAnimation = false
                        canCloseWin = true
                    }
                }
            }
        }
    ) {

        val (image, bottomButtonPlace, tips) = createRefs()


        AnimatedVisibility(
            visible = visibleAnimation,
            enter = slideInVertically() + fadeIn(tween(300)),
            exit = slideOutVertically() + fadeOut(tween(300)),
            modifier = Modifier.constrainAs(image) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
        ) {
            AsyncImage(
                model = url,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .blur(UiConst.DEFAULT_BLUR),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(.5f)),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = url,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            if (!isShowTips) {

                TipSwipeVertical{
                    isShowTips = !isShowTips
                }
            }

        }

        AnimatedVisibility(
            visible = visibleAnimation,
            enter = slideInVertically{ it / 2 } + fadeIn(tween(300)),
            exit = slideOutHorizontally { it / 2 } + fadeOut(tween(300)),
            modifier = Modifier.constrainAs(bottomButtonPlace){
                bottom.linkTo(parent.bottom)
            }
        ) {
            BottomButtonPlace(
                onDownload = { viewModel.downloadImage(url = url, context = context) },
            )

        }





    }

    LaunchedEffect(
        key1 = Unit,
        key2 = canCloseWin,
        block = {
            delay(300)
            visibleAnimation = true
            if (canCloseWin) { onBack() }
        })

}

@Composable
private fun BottomButtonPlace(
    onDownload: () -> Unit,
) {
    AppButton(
        modifier = Modifier.fillMaxWidth(),
        text = "Download",
        onClick = { onDownload() }, bgColor = Color.Green
    )
}